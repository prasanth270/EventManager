package dbo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Batman on 2/21/16.
 */
public class Domain extends SQLiteOpenHelper{

    private SQLiteDatabase database;
    public static final int database_version = 2;
    public static final String CREATE_QUERY = "CREATE TABLE " + TableInfo.TableDetails.TABLE_NAME
            + "(" + TableInfo.TableDetails.EVENT_DATE + " TEXT, " + TableInfo.TableDetails.EVENT_NAME
            + " TEXT);";

    public Domain(Context context) {
        super(context, TableInfo.TableDetails.DATABASE_NAME, null, database_version);
        Log.d("DataBase Operations", "Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("DataBase Operations", "Create Table Query");
        Log.d("Database Query", CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableInfo.TableDetails.TABLE_NAME);
        onCreate(db);
    }

    /**
     * Save New Event to Database
     * @param dbOperations
     * @param eventText
     * @param eventDate
     */
    public void saveEvent(Domain dbOperations, String eventText, String eventDate){
        Log.d("Event text : ", eventText);
        Log.d("Event Date : ", eventDate);
        database = dbOperations.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableInfo.TableDetails.EVENT_NAME, eventText);
        contentValues.put(TableInfo.TableDetails.EVENT_DATE, eventDate);
        long success = database.insert(TableInfo.TableDetails.TABLE_NAME, null, contentValues);
        Log.d("Database Insert Status", "Value : " + success);
    }

    /**
     * Get All The events from Database
     * @param dbOperations
     * @return
     */
    public Cursor getEvents(Domain dbOperations){
        database = dbOperations.getReadableDatabase();
        String[] columns = {TableInfo.TableDetails.EVENT_NAME, TableInfo.TableDetails.EVENT_DATE};
        Cursor cursor = database.rawQuery("SELECT event_name, event_date FROM events", null);
        return cursor;
    }
}
