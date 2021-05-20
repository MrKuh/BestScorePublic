package project.bestscore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import project.bestscore.ui.parcours.Parcour;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bestScore_database";

    private static final String TEAMMATE = "Teammate";
    private static final String TEAMMATE_ID = "teammate_id";
    private static final String TEAMMATE_NAME = "teammate_name";
    private static final String TEAMMATE_WINS = "teammate_wins";

    private static final String PARCOUR = "Parcour";
    private static final String PARCOUR_ID = "parcour_id";
    private static final String PARCOUR_NAME = "parcour_name";

    private static final String EVENT = "Event";
    private static final String EVENT_ID = "event_id";
    private static final String EVENT_NAME = "event_name";
    private static final String EVENT_TYPE = "event_type";
    private static final String EVENT_DATE = "event_date";

    private static final String TARGET = "Target";
    private static final String TARGET_ID = "target_id";
    private static final String TARGET_NAME = "target_id";

    private static final String TEAMMATE_EVENT = "TeammateEvent";
    private static final String TEAMMATE_EVENT_TARGET = "TeammateEventTarget";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String teammate = "CREATE TABLE " + TEAMMATE +
                "(" + TEAMMATE_ID + " INTEGER PRIMARY KEY," + TEAMMATE_NAME + " TEXT NOT NULL UNIQUE," + TEAMMATE_WINS + " INTEGER)";

        String event = "CREATE TABLE " + EVENT +
                "(" + EVENT_ID + " INTEGER PRIMARY KEY, " + EVENT_NAME + " TEXT NOT NULL UNIQUE, " + EVENT_TYPE + " TEXT, " + EVENT_DATE + " TEXT)";

        String target = "CREATE TABLE " + TARGET +
                "(" + TARGET_ID + " INTEGER PRIMARY KEY," + EVENT_ID + " INTEGER,FOREIGN KEY(" + EVENT_ID + ") REFERENCES "
                + EVENT + "(" + EVENT_ID + "))";

        String parcour = "CREATE TABLE " + PARCOUR +
                "(" + PARCOUR_ID + " INTEGER PRIMARY KEY," + PARCOUR_NAME + " TEXT NOT NULL UNIQUE)";

        String teammate_event = "CREATE TABLE " + TEAMMATE_EVENT +
                "(" + TEAMMATE_ID + " INTEGER, " + EVENT_ID + " INTEGER," +
                "PRIMARY KEY (" + TEAMMATE_ID + "," + EVENT_ID + ")," +
                "FOREIGN KEY(" + TEAMMATE_ID + ") REFERENCES " + TEAMMATE + "(" + TEAMMATE_ID + "), " +
                "FOREIGN KEY(" + EVENT_ID + ") REFERENCES " + EVENT + "(" + EVENT_ID + ")" + ")";

        String teammate_event_target = "CREATE TABLE " + TEAMMATE_EVENT_TARGET +
                "(" + TEAMMATE_ID + " INTEGER, " + EVENT_ID + " INTEGER, " + TARGET_ID + " INTEGER, " +
                "PRIMARY KEY (" + TEAMMATE_ID + ", " + EVENT_ID + ", " + TARGET_ID + ")," +
                "FOREIGN KEY(" + TEAMMATE_ID + ") REFERENCES " + TEAMMATE + "(" + TEAMMATE_ID + "), " +
                "FOREIGN KEY(" + EVENT_ID + ") REFERENCES " + EVENT + "(" + EVENT_ID + ")," +
                "FOREIGN KEY(" + TARGET_ID + ") REFERENCES " + TARGET + "(" + TARGET_ID + ") " + ")";

        db.execSQL(teammate);
        db.execSQL(event);
        db.execSQL(target);
        db.execSQL(parcour);
        db.execSQL(teammate_event);
        db.execSQL(teammate_event_target);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TEAMMATE);
        db.execSQL("DROP TABLE IF EXISTS " + PARCOUR);
        db.execSQL("DROP TABLE IF EXISTS " + EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TARGET);
        db.execSQL("DROP TABLE IF EXISTS " + TEAMMATE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TEAMMATE_EVENT_TARGET);
        onCreate(db);
    }

    public boolean insertTeammate(@NotNull Teammate teammate) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues teammate_values = new ContentValues();
        teammate_values.put(TEAMMATE_NAME, teammate.getName());
        teammate_values.put(TEAMMATE_WINS, teammate.getWins());

        long result = sqLiteDatabase.insert(TEAMMATE, null, teammate_values);

        if (result == -1) {
            return false;
        }
        teammate.setId(getTeammateId(teammate.getName()));

        return true;
    }

    public boolean teammateInserted(Teammate teammate){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TEAMMATE, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String teammate_name = cursor.getString(cursor.getColumnIndex(TEAMMATE_NAME));
            int teammate_id = cursor.getInt(cursor.getColumnIndex(TEAMMATE_ID));

            if(teammate_id == teammate.getId() || teammate_name.equals(teammate.getName())){
                return true;
            }

            cursor.moveToNext();
        }

        return false;
    }

    public ArrayList getTeammates() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Teammate> teammateList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TEAMMATE, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String teammate_name = cursor.getString(cursor.getColumnIndex(TEAMMATE_NAME));
            int teammate_wins = cursor.getInt(cursor.getColumnIndex(TEAMMATE_WINS));
            int teammate_id = cursor.getInt(cursor.getColumnIndex(TEAMMATE_ID));
            teammateList.add(new Teammate(teammate_name, teammate_wins, teammate_id));
            cursor.moveToNext();
        }
        return teammateList;
    }

    public int getTeammateId(String teammateName){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT " +  TEAMMATE_ID + " FROM " + TEAMMATE + " WHERE " + TEAMMATE_NAME +  " = \""
                + teammateName + "\"", null);

        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex(TEAMMATE_ID));
    }

    public boolean updateTeammate(@NotNull Teammate teammate){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TEAMMATE_NAME, teammate.getName());
        cv.put(TEAMMATE_WINS, teammate.getWins());

        long result = db.update(TEAMMATE, cv, TEAMMATE_ID + "=?", new String[]{String.valueOf(teammate.getId())});

        if(result == -1){
            return false;
        }

        return true;

    }

    public boolean deleteTeammate(@NotNull Teammate teammate){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TEAMMATE, TEAMMATE_ID + "=?", new String[]{String.valueOf(teammate.getId())});

        if(result == -1){
            return false;
        }

        return true;
    }

    public boolean insertParcour(@NotNull Parcour parcour) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues parcour_values = new ContentValues();
        parcour_values.put(PARCOUR_NAME, parcour.getParcourName());

        long result = sqLiteDatabase.insert(PARCOUR, null, parcour_values);

        if (result == -1) {
            return false;
        }
        parcour.setId(getParcourID(parcour.getParcourName()));

        return true;
    }

    public boolean parcourInserted(Parcour parcour){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + PARCOUR, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String parcour_name = cursor.getString(cursor.getColumnIndex(PARCOUR_NAME));
            int parcour_id = cursor.getInt(cursor.getColumnIndex(PARCOUR_ID));

            if(parcour_id == parcour.getId() || parcour_name.equals(parcour.getParcourName())){
                return true;
            }

            cursor.moveToNext();
        }

        return false;
    }

    public ArrayList getParcour() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Parcour> parcourList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + PARCOUR, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String parcour_name = cursor.getString(cursor.getColumnIndex(PARCOUR_NAME));
            int parcour_id = cursor.getInt(cursor.getColumnIndex(PARCOUR_ID));
            parcourList.add(new Parcour(parcour_name, parcour_id));
            cursor.moveToNext();
        }
        return parcourList;
    }

    public int getParcourID(String parcourName){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT " +  PARCOUR_ID + " FROM " + PARCOUR + " WHERE " + PARCOUR_NAME , null);

        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex(PARCOUR_ID));
    }

    public boolean updateParcour(@NotNull Parcour parcour){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PARCOUR_NAME, parcour.getParcourName());

        long result = db.update(PARCOUR, cv, PARCOUR_ID + "=?", new String[]{String.valueOf(parcour.getId())});

        if(result == -1){
            return false;
        }

        return true;

    }

    public boolean deleteParcour(@NotNull Parcour parcour){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(PARCOUR, PARCOUR_ID + "=?", new String[]{String.valueOf(parcour.getId())});

        if(result == -1){
            return false;
        }

        return true;
    }


    public boolean insertEvent(@NotNull Event event) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues event_values = new ContentValues();
        event_values.put(EVENT_NAME, event.getName());
        event_values.put(EVENT_TYPE, event.getType());
        event_values.put(EVENT_DATE, event.getDate().format(formatter));

        long result = sqLiteDatabase.insert(EVENT, null, event_values);

        if (result == -1) {
            return false;
        }
        event.setId(getEventId(event.getName()));

        ContentValues teammate_event_values = new ContentValues();

        ArrayList<Teammate> teammateList = event.getTeammateList();

        for(int i = 0; i < teammateList.size(); i++){

            teammate_event_values.put(TEAMMATE_ID, teammateList.get(i).getId());
            teammate_event_values.put(EVENT_ID, event.getId());

            result = sqLiteDatabase.insert(TEAMMATE_EVENT, null, teammate_event_values);

            if (result == -1) {
                return false;
            }
        }

        return true;
    }

    public boolean eventInserted(Event event){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + EVENT, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String event_name = cursor.getString(cursor.getColumnIndex(EVENT_NAME));
            int event_id = cursor.getInt(cursor.getColumnIndex(EVENT_ID));

            if(event_id == event.getId() || event_name.equals(event.getName())){
                return true;
            }

            cursor.moveToNext();
        }

        return false;
    }

    public ArrayList<Event> getEvents() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Event> eventList = new ArrayList<>();
        ArrayList<Teammate> teammateList = new ArrayList<>();

        Cursor eventCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + EVENT, null);
        Cursor teammateCursor;
        eventCursor.moveToFirst();

        while (!eventCursor.isAfterLast()) {
            int event_id = eventCursor.getInt(eventCursor.getColumnIndex(EVENT_ID));
            String event_name = eventCursor.getString(eventCursor.getColumnIndex(EVENT_NAME));
            String event_type = eventCursor.getString(eventCursor.getColumnIndex(EVENT_TYPE));

            LocalDateTime event_date;

                event_date = LocalDateTime.parse(eventCursor.getString(eventCursor.getColumnIndex(EVENT_DATE)), formatter);

            teammateCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TEAMMATE +
                    " WHERE " + TEAMMATE_ID + " IN (SELECT " + TEAMMATE_ID + " FROM " + TEAMMATE_EVENT +
                    " WHERE " + EVENT_ID + " = " + event_id + ");", null);

            teammateList = new ArrayList<>();

            teammateCursor.moveToFirst();

            while (!teammateCursor.isAfterLast()) {

                int teammate_wins = teammateCursor.getInt(teammateCursor.getColumnIndex(TEAMMATE_WINS));
                int teammate_id = teammateCursor.getInt(teammateCursor.getColumnIndex(TEAMMATE_ID));
                String teammate_name = teammateCursor.getString(teammateCursor.getColumnIndex(TEAMMATE_NAME));
                teammateList.add(new Teammate(teammate_name, teammate_wins, teammate_id));
                teammateCursor.moveToNext();
            }

                eventList.add(new Event(event_name, event_type, event_date, teammateList, null, event_id));

            eventCursor.moveToNext();
        }
        return eventList;
    }

    public int getEventId(String eventName){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT " + EVENT_ID + " FROM " + EVENT + " WHERE " + EVENT_NAME + " = \""
                + eventName + "\"", null);
        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex(EVENT_ID));
    }
}
