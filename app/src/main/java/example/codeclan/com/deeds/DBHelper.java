package example.codeclan.com.deeds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 13/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "deeds.db";
    public static final String DEEDS_TABLE_NAME = "deeds";
    public static final String DEEDS_COLUMN_ID = "id";
    public static final String DEEDS_COLUMN_NAME = "name";
    public static final String DEEDS_COLUMN_DATE = "date";
    public static final String DEEDS_COLUMN_DETAILS = "details";
    public static final String DEEDS_COLUMN_COMPLETE = "complete";
    private HashMap hp;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 3);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + DEEDS_TABLE_NAME + "(id INTEGER primary key autoincrement, name TEXT, date TEXT, details TEXT, complete TEXT )");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + DEEDS_TABLE_NAME);
        onCreate(db);
    }


}