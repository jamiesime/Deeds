package example.codeclan.com.deeds;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;

import java.util.ArrayList;

import static example.codeclan.com.deeds.DBHelper.DEEDS_COLUMN_COMPLETE;
import static example.codeclan.com.deeds.DBHelper.DEEDS_COLUMN_ID;
import static example.codeclan.com.deeds.DBHelper.DEEDS_COLUMN_NAME;
import static example.codeclan.com.deeds.DBHelper.DEEDS_TABLE_NAME;

/**
 * Created by user on 13/11/2017.
 */

public class Deed {

    private Integer id;
    private String name;
    private String complete;

    public Deed(String name, String complete) {
        this.name = name;
        this.complete = complete;
    }

    public Deed(Integer id, String name, String complete) {
        this.id = id;
        this.name = name;
        this.complete = complete;
    }

    public String getName() {
        return name;
    }

    public String getComplete() {
        return complete;
    }

    public boolean save(DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DEEDS_COLUMN_NAME, this.name);
        cv.put(DEEDS_COLUMN_COMPLETE, this.complete);
        db.insert(DEEDS_TABLE_NAME, null, cv);

        return true;
    }

    public static ArrayList<Deed> all(DBHelper dbHelper){
        ArrayList<Deed> deeds = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DEEDS_TABLE_NAME, null);
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DEEDS_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_NAME));
            String complete = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_COMPLETE));
            Deed deed = new Deed(id, name, complete);
            deeds.add(deed);
        }
        cursor.close();
        return deeds;
    }

    public static boolean deleteAll(DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + DEEDS_TABLE_NAME);
        return true;
    }

    public static boolean delete(DBHelper dbHelper, Integer id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = " id = ?";
        String[] values = {id.toString()};
        db.delete(DEEDS_TABLE_NAME, selection, values);
        return true;
    }

}
