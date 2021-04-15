package project.bestscore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.contentcapture.DataShareWriteAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bestScore_database";
    private static final String TEAMMATE = "Teammate";
    private static final String EVENT = "Event";
    private static final String TARGET = "Target";

    private static final String TEAMMATE_EVENT = "TeammateEvent";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String teammate = "CREATE TABLE " + TEAMMATE +
                "(teammate_id INTEGER PRIMARY KEY,teammate_name TEXT,teammate_wins INTEGER)";

        String event = "CREATE TABLE " + EVENT +
                "(event_id INTEGER PRIMARY KEY, event_type TEXT, event_date TEXT)";

        String target = "CREATE TABLE " + TARGET +
                "(target_id INTEGER PRIMARY KEY,event_id INTEGER,FOREIGN KEY(event_id) REFERENCES "
                + EVENT + "(event_id) )";


        String teammate_event = "CREATE TABLE " + TEAMMATE_EVENT +
                "(teammate_id INTEGER, event_id INTEGER," + "PRIMARY KEY (teammate_id, event_id)," +
                "FOREIGN KEY(teammate_id) REFERENCES " + TEAMMATE + "(teammate_id), " +
                "FOREIGN KEY(event_id) REFERENCES " + EVENT + "(event_id)" + ")";

        db.execSQL(teammate);
        db.execSQL(event);
        db.execSQL(target);
        db.execSQL(teammate_event);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TEAMMATE);
        db.execSQL("DROP TABLE IF EXISTS "+EVENT);
        db.execSQL("DROP TABLE IF EXISTS "+TARGET);
        db.execSQL("DROP TABLE IF EXISTS "+TEAMMATE_EVENT);
        onCreate(db);
    }

    public boolean insertTeammate(Teammate teammate){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues teammate_values = new ContentValues();
        teammate_values.put("teammate_name", teammate.getName());
        teammate_values.put("teammate_wins", teammate.getWins());

        long result = sqLiteDatabase.insert(TEAMMATE, null, teammate_values);

        if(result == -1){
            Toast.makeText(context, "Inserting Failed", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(context,"Inserting Successfull", Toast.LENGTH_SHORT).show();
        return true;
    }

    public ArrayList getTeammates(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Teammate> teammateList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TEAMMATE, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String teammate_name = cursor.getString(cursor.getColumnIndex("teammate_name"));
            int teammate_wins = cursor.getInt(cursor.getColumnIndex("teammate_wins"));
            int teamate_id = cursor.getInt(cursor.getColumnIndex("teammate_id"));
            //teammateList.add(new Teammate(teammate_name, teammate_wins, teamate_id));
            cursor.moveToNext();
        }

        return teammateList;
    }

    public boolean insertEvent(Event event){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues event_values = new ContentValues();
        event_values.put("event_type", event.getType());
        event_values.put("event_date", event.getDate().format(formatter));

        sqLiteDatabase.insert(EVENT, null, event_values);

        ContentValues teammate_event_values = new ContentValues();
        ArrayList<Teammate> teammateList = event.getTeammateList();

        for(int i = 0; i < teammateList.size(); i++){
            teammate_event_values.put("event_id", event_values.getAsInteger("event_id"));
            //teammate_event_values.put("teammate_id", teammateList.get(i).getId());
        }

        sqLiteDatabase.insert(TEAMMATE_EVENT, null, teammate_event_values);

        return true;
    }

    public ArrayList getEvents(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Event> eventList = new ArrayList<>();
        ArrayList<Teammate> teammateList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + EVENT, null);
        Cursor teammateCursor;
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String event_type = cursor.getString(cursor.getColumnIndex("event_type"));
            LocalDate event_date = LocalDate.parse(cursor.getString(cursor.getColumnIndex("event_date")),formatter);
            int id = cursor.getInt(cursor.getColumnIndex("event_id"));

            teammateCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TEAMMATE + " " +
                    "WHERE teammate_id = (SELECT teammate_id FROM " + TEAMMATE_EVENT +
                    " WHERE event_id = " + id + ")", null);

            teammateList.clear();

            while(!teammateCursor.isAfterLast()){
                String teammate_name = cursor.getString(cursor.getColumnIndex("teammate_name"));
                int teammate_wins = cursor.getInt(cursor.getColumnIndex("teammate_wins"));
                int teamate_id = cursor.getInt(cursor.getColumnIndex("teammate_id"));
                //teammateList.add(new Teammate(teammate_name, teammate_wins, teamate_id));
                cursor.moveToNext();
            }

            if(event_type.equals("Archery")){
                //eventList.add(new Event_Archery(event_date, teammateList, id));
            }

            cursor.moveToNext();
        }

        return eventList;
    }




}
