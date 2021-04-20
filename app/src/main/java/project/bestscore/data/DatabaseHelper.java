package project.bestscore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.contentcapture.DataShareWriteAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bestScore_database";
    private static final String TEAMMATE = "Teammate";
    private static final String EVENT = "Event";
    private static final String TARGET = "Target";

    private static final String TEAMMATE_EVENT = "TeammateEvent";
    private static final String TEAMMATE_EVENT_TARGET = "TeammateEventTarget";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String teammate = "CREATE TABLE " + TEAMMATE +
                "(teammate_id INTEGER PRIMARY KEY,teammate_name TEXT NOT NULL UNIQUE,teammate_wins INTEGER)";

        String event = "CREATE TABLE " + EVENT +
                "(event_id INTEGER PRIMARY KEY, event_name TEXT NOT NULL UNIQUE, event_type TEXT, event_date TEXT)";

        String target = "CREATE TABLE " + TARGET +
                "(target_id INTEGER PRIMARY KEY,event_id INTEGER,FOREIGN KEY(event_id) REFERENCES "
                + EVENT + "(event_id) )";

        String teammate_event = "CREATE TABLE " + TEAMMATE_EVENT +
                "(teammate_id INTEGER, event_id INTEGER," + "PRIMARY KEY (teammate_id, event_id)," +
                "FOREIGN KEY(teammate_id) REFERENCES " + TEAMMATE + "(teammate_id), " +
                "FOREIGN KEY(event_id) REFERENCES " + EVENT + "(event_id)" + ")";

        String teammate_event_target = "CREATE TABLE " + TEAMMATE_EVENT_TARGET +
                "(teammate_id INTEGER, event_id INTEGER, target_id INTEGER, " + "PRIMARY KEY (teammate_id, event_id, target_id)," +
                "FOREIGN KEY(teammate_id) REFERENCES " + TEAMMATE + "(teammate_id), " +
                "FOREIGN KEY(event_id) REFERENCES " + EVENT + "(event_id)," +
                "FOREIGN KEY(target_id) REFERENCES " + TARGET + "(target_id) " + ")";

        db.execSQL(teammate);
        db.execSQL(event);
        db.execSQL(target);
        db.execSQL(teammate_event);
        db.execSQL(teammate_event_target);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TEAMMATE);
        db.execSQL("DROP TABLE IF EXISTS " + EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TARGET);
        db.execSQL("DROP TABLE IF EXISTS " + TEAMMATE_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TEAMMATE_EVENT_TARGET);
        onCreate(db);
    }

    public boolean insertTeammate(Teammate teammate) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues teammate_values = new ContentValues();
        teammate_values.put("teammate_name", teammate.getName());
        teammate_values.put("teammate_wins", teammate.getWins());

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
            String teammate_name = cursor.getString(cursor.getColumnIndex("teammate_name"));
            int teammate_id = cursor.getInt(cursor.getColumnIndex("teammate_id"));

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
            String teammate_name = cursor.getString(cursor.getColumnIndex("teammate_name"));
            int teammate_wins = cursor.getInt(cursor.getColumnIndex("teammate_wins"));
            int teammate_id = cursor.getInt(cursor.getColumnIndex("teammate_id"));
            teammateList.add(new Teammate(teammate_name, teammate_wins, teammate_id));
            cursor.moveToNext();
        }
        return teammateList;
    }

    public int getTeammateId(String teammateName){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT teammate_id FROM " + TEAMMATE + " WHERE teammate_name = \""
                + teammateName + "\"", null);

        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex("teammate_id"));
    }

    public boolean insertEvent(Event event) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues event_values = new ContentValues();
        event_values.put("event_name", event.getName());
        event_values.put("event_type", event.getType());
        event_values.put("event_date", event.getDate().format(formatter));

        long result = sqLiteDatabase.insert(EVENT, null, event_values);

        if (result == -1) {
            Toast.makeText(context, "Inserting Failed", Toast.LENGTH_SHORT).show();
            return false;
        }
        event.setId(getEventId(event.getName()));

        ContentValues teammate_event_values = new ContentValues();

        ArrayList<Teammate> teammateList = event.getTeammateList();

        for(int i = 0; i < teammateList.size(); i++){

            teammate_event_values.put("teammate_id", teammateList.get(i).getId());
            teammate_event_values.put("event_id", event.getId());

            result = sqLiteDatabase.insert(TEAMMATE_EVENT, null, teammate_event_values);

            if (result == -1) {
                Toast.makeText(context, "Inserting Failed", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        Toast.makeText(context, "Inserting Successful", Toast.LENGTH_SHORT).show();
        return true;
    }

    public ArrayList getEvents() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Event> eventList = new ArrayList<>();
        ArrayList<Teammate> teammateList = new ArrayList<>();

        Cursor eventCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + EVENT, null);
        Cursor teammateCursor;
        eventCursor.moveToFirst();
        while (!eventCursor.isAfterLast()) {
            String event_name = eventCursor.getString(eventCursor.getColumnIndex("event_name"));
            String event_type = eventCursor.getString(eventCursor.getColumnIndex("event_type"));

            LocalDateTime event_date;

            try{
                event_date = LocalDateTime.parse(eventCursor.getString(eventCursor.getColumnIndex("event_date")), formatter);
            } catch (DateTimeException e){
                event_date = null;
            }

            int event_id = eventCursor.getInt(eventCursor.getColumnIndex("event_id"));

            System.out.println("SELECT * FROM " + TEAMMATE + " " +
                    "WHERE teammate_id IN (SELECT teammate_id FROM " + TEAMMATE_EVENT +
                    " WHERE event_id = " + event_id + ")");

            teammateCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TEAMMATE + " " +
                    "WHERE teammate_id IN (SELECT teammate_id FROM " + TEAMMATE_EVENT +
                    " WHERE event_id = " + event_id + ");", null);

            teammateList.clear();

            teammateCursor.moveToFirst();

            while (!teammateCursor.isAfterLast()) {

                int teammate_wins = teammateCursor.getInt(teammateCursor.getColumnIndex("teammate_wins"));
                int teamate_id = teammateCursor.getInt(teammateCursor.getColumnIndex("teammate_id"));
                String teammate_name = teammateCursor.getString(teammateCursor.getColumnIndex("teammate_name"));
                teammateList.add(new Teammate(teammate_name, teammate_wins, teamate_id));
                teammateCursor.moveToNext();
            }

                eventList.add(new Event(event_name, event_type, event_date, teammateList, null, event_id));

            eventCursor.moveToNext();
        }
        return eventList;
    }

    public int getEventId(String eventName){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT event_id FROM " + EVENT + " WHERE event_name = \""
                + eventName + "\"", null);
        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex("event_id"));
    }

}
