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

import project.bestscore.ui.home.selection.parcour.Parcour;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bestScore_database";

    private static final String TEAMMATE = "teammate";
    private static final String TEAMMATE_ID = "teammate_id";
    private static final String TEAMMATE_NAME = "teammate_name";

    private static final String PARCOUR = "parcour";
    private static final String PARCOUR_ID = "parcour_id";
    private static final String PARCOUR_NAME = "parcour_name";

    private static final String EVENT = "event";
    private static final String EVENT_ID = "event_id";
    private static final String EVENT_NAME = "event_name";
    private static final String EVENT_TYPE = "event_type";
    private static final String EVENT_DATE = "event_date";
    private static final String EVENT_PARCOUR_ID = "event_parcour_id";

    private static final String TARGET = "target";
    private static final String TARGET_ID = "target_id";
    private static final String TARGET_NAME = "target_name";
    private static final String TARGET_INDEX = "target_number";

    private static final String TEAMMATE_EVENT = "teammateevent";
    private static final String TEAMMATE_EVENT_TARGET = "teammateeventtarget";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String teammate = "CREATE TABLE " + TEAMMATE +
                "(" + TEAMMATE_ID + " INTEGER PRIMARY KEY," + TEAMMATE_NAME + " TEXT NOT NULL UNIQUE)";

        String parcour = "CREATE TABLE " + PARCOUR +
                "(" + PARCOUR_ID + " INTEGER PRIMARY KEY," + PARCOUR_NAME + " TEXT NOT NULL UNIQUE)";

        String event = "CREATE TABLE " + EVENT +
                "(" + EVENT_ID + " INTEGER PRIMARY KEY, " + EVENT_NAME + " TEXT NOT NULL UNIQUE, " +
                EVENT_TYPE + " TEXT, " + EVENT_DATE + " TEXT,  " +
                "FOREIGN KEY(" + EVENT_PARCOUR_ID + ") REFERENCES " + PARCOUR + "(" + PARCOUR_ID + "))";

        String target = "CREATE TABLE " + TARGET +
                "(" + TARGET_INDEX + " INTEGER PRIMARY KEY," + TARGET_NAME + " TEXT NOT NULL, " +
                "FOREIGN KEY(" + EVENT_ID + ") REFERENCES " + EVENT + "(" + EVENT_ID + "))";

        String teammate_event = "CREATE TABLE " + TEAMMATE_EVENT +
                "(" + TEAMMATE_ID + " INTEGER, " + EVENT_ID + " INTEGER," +
                "PRIMARY KEY (" + TEAMMATE_ID + "," + EVENT_ID + ")," +
                "FOREIGN KEY(" + TEAMMATE_ID + ") REFERENCES " + TEAMMATE + "(" + TEAMMATE_ID + "), " +
                "FOREIGN KEY(" + EVENT_ID + ") REFERENCES " + EVENT + "(" + EVENT_ID + ")" + ")";

        String teammate_event_target = "CREATE TABLE " + TEAMMATE_EVENT_TARGET +
                "(" + TEAMMATE_ID + " INTEGER, " + EVENT_ID + " INTEGER, " + TARGET_INDEX + " INTEGER, " +
                "PRIMARY KEY (" + TEAMMATE_ID + ", " + EVENT_ID + ", " + TARGET_INDEX + ")," +
                "FOREIGN KEY(" + TEAMMATE_ID + ") REFERENCES " + TEAMMATE + "(" + TEAMMATE_ID + "), " +
                "FOREIGN KEY(" + EVENT_ID + ") REFERENCES " + EVENT + "(" + EVENT_ID + ")," +
                "FOREIGN KEY(" + TARGET_ID + ") REFERENCES " + TARGET + "(" + TARGET_ID + ") " + ")";

        db.execSQL(teammate);
        db.execSQL(parcour);
        db.execSQL(event);
        db.execSQL(target);
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

    //TEAMMATE--------------------------------------------------------------------------------------

    public boolean insertTeammate(@NotNull Teammate teammate) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues teammate_values = new ContentValues();
        teammate_values.put(TEAMMATE_NAME, teammate.getName());

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

    public ArrayList<Teammate> getTeammates() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Teammate> teammateList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TEAMMATE, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String teammate_name = cursor.getString(cursor.getColumnIndex(TEAMMATE_NAME));
            int teammate_id = cursor.getInt(cursor.getColumnIndex(TEAMMATE_ID));
            teammateList.add(new Teammate(teammate_name, teammate_id));
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

    public int getEventsPlayed(@NotNull Teammate teammate){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TEAMMATE_EVENT + " WHERE " + TEAMMATE_ID + " = " + teammate.getId(), null);

        return cursor.getCount();
    }

    //PARCOUR---------------------------------------------------------------------------------------

    public boolean insertParcour(@NotNull Parcour parcour) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PARCOUR_NAME, parcour.getName());

        long result = sqLiteDatabase.insert(PARCOUR, null, values);

        if (result == -1) {
            return false;
        }
        parcour.setId(getParcourId(parcour.getName()));

        return true;
    }

    public boolean parcourInserted(Parcour parcour){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + PARCOUR, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String parcour_name = cursor.getString(cursor.getColumnIndex(PARCOUR_NAME));
            int parcour_id = cursor.getInt(cursor.getColumnIndex(PARCOUR_ID));

            if(parcour_id == parcour.getId() || parcour_name.equals(parcour.getName())){
                return true;
            }

            cursor.moveToNext();
        }

        return false;
    }

    public ArrayList<Parcour> getParcours() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Parcour> parcourList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + PARCOUR, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String parcour_name = cursor.getString(cursor.getColumnIndex(PARCOUR_NAME));
            int parcour_id = cursor.getInt(cursor.getColumnIndex(PARCOUR_ID));
            parcourList.add(new Parcour(parcour_id, parcour_name));
            cursor.moveToNext();
        }
        return parcourList;
    }

    public int getParcourId(String parcourName){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT " +  PARCOUR_ID + " FROM " + PARCOUR + " WHERE " + PARCOUR_NAME +  " = \""
                + parcourName + "\"", null);

        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex(PARCOUR_ID));
    }

    //TARGET----------------------------------------------------------------------------------------

    public boolean insertTarget(@NotNull Target target, @NotNull int eventId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues target_values = new ContentValues();
        target_values.put(TARGET_NAME, target.getName());
        target_values.put(TARGET_INDEX, target.getIndex());
        target_values.put(EVENT_ID, eventId);


        long result = sqLiteDatabase.insert(TARGET, null, target_values);

        if (result == -1) {
            return false;
        }
        target.setId(getTargetId(target.getIndex(), eventId));

        return true;
    }

    public int getTargetId(int targetIndex, int eventId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT " +  TARGET_ID + " FROM " + TARGET +
                " WHERE " + TARGET_ID +  " = \"" + targetIndex + "\" && " +
                EVENT_ID + " = \"" + eventId + "\"", null);

        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex(TARGET_ID));
    }

    //EVENT-----------------------------------------------------------------------------------------

    public boolean insertEvent(@NotNull Event event) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues event_values = new ContentValues();
        event_values.put(EVENT_NAME, event.getName());
        event_values.put(EVENT_TYPE, event.getType());
        event_values.put(EVENT_DATE, event.getDate().format(formatter));
        event_values.put(EVENT_PARCOUR_ID, event.getParcour().getId());

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
        LocalDateTime event_date;
        Parcour event_parcour;

        Cursor eventCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + EVENT, null);
        Cursor teammateCursor;
        Cursor parcourCursor;
        eventCursor.moveToFirst();

        while (!eventCursor.isAfterLast()) {
            int event_id = eventCursor.getInt(eventCursor.getColumnIndex(EVENT_ID));
            String event_name = eventCursor.getString(eventCursor.getColumnIndex(EVENT_NAME));
            String event_type = eventCursor.getString(eventCursor.getColumnIndex(EVENT_TYPE));

            parcourCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + PARCOUR +
                    " WHERE " + PARCOUR_ID + " = (SELECT " + EVENT_PARCOUR_ID + " FROM " + EVENT +
                    " WHERE " + EVENT_ID + " = " + event_id + ");", null);

            event_parcour = new Parcour(
                    parcourCursor.getInt(parcourCursor.getColumnIndex(PARCOUR_ID)),
                    parcourCursor.getString(parcourCursor.getColumnIndex(PARCOUR_NAME)));

            event_date = LocalDateTime.parse(eventCursor.getString(eventCursor.getColumnIndex(EVENT_DATE)), formatter);

            teammateCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TEAMMATE +
                    " WHERE " + TEAMMATE_ID + " IN (SELECT " + TEAMMATE_ID + " FROM " + TEAMMATE_EVENT +
                    " WHERE " + EVENT_ID + " = " + event_id + ");", null);

            teammateList = new ArrayList<>();

            teammateCursor.moveToFirst();

            while (!teammateCursor.isAfterLast()) {

                int teammate_id = teammateCursor.getInt(teammateCursor.getColumnIndex(TEAMMATE_ID));
                String teammate_name = teammateCursor.getString(teammateCursor.getColumnIndex(TEAMMATE_NAME));
                teammateList.add(new Teammate(teammate_name, teammate_id));
                teammateCursor.moveToNext();
            }

            eventList.add(new Event(event_name, event_type, event_parcour, event_date, teammateList, null, event_id));

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

