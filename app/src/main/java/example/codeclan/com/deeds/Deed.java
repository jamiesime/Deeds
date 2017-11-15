package example.codeclan.com.deeds;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import static example.codeclan.com.deeds.DBHelper.DEEDS_COLUMN_COMPLETE;
import static example.codeclan.com.deeds.DBHelper.DEEDS_COLUMN_DATE;
import static example.codeclan.com.deeds.DBHelper.DEEDS_COLUMN_DETAILS;
import static example.codeclan.com.deeds.DBHelper.DEEDS_COLUMN_ID;
import static example.codeclan.com.deeds.DBHelper.DEEDS_COLUMN_NAME;
import static example.codeclan.com.deeds.DBHelper.DEEDS_TABLE_NAME;
import static java.util.Collections.min;
import static java.util.Collections.sort;

/**
 * Created by user on 13/11/2017.
 */

public class Deed {

    private Integer id;
    private String name;
    private String date;
    private String complete;
    private String details;

    public Deed(String name, String date, String details, String complete) {
        this.name = name;
        this.date = date;
        this.details = details;
        this.complete = complete;
    }

    public Deed(Integer id, String name, String date, String details, String complete) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.details = details;
        this.complete = complete;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDetails(){
        return details;
    }

    public Integer getId() {
        return id;
    }

    public String getComplete() {
        return complete;
    }

    public boolean save(DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DEEDS_COLUMN_NAME, this.name);
        cv.put(DEEDS_COLUMN_DATE, this.date);
        cv.put(DEEDS_COLUMN_DETAILS, this.details);
        cv.put(DEEDS_COLUMN_COMPLETE, this.complete);
        db.insert(DEEDS_TABLE_NAME, null, cv);
        return true;
    }

    public boolean update(DBHelper dbHelper, Integer id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DEEDS_COLUMN_NAME, this.name);
        cv.put(DEEDS_COLUMN_DATE, this.date);
        cv.put(DEEDS_COLUMN_DETAILS, this.details);
        cv.put(DEEDS_COLUMN_COMPLETE, this.complete);
        db.update(DEEDS_TABLE_NAME, cv, "id="+id, null);
        return true;
    }

    public static ArrayList<Deed> all(DBHelper dbHelper){
        ArrayList<Deed> deeds = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DEEDS_TABLE_NAME, null);
//        cursor.moveToFirst();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DEEDS_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_NAME));
            String date = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_DATE));
            String details = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_DETAILS));
            String complete = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_COMPLETE));
            Deed deed = new Deed(id, name, date, details, complete);
            deeds.add(deed);
        }
        cursor.close();
        deeds = sortByDate(deeds);
        return deeds;
    }

    public static ArrayList<Deed> allComplete(DBHelper dbHelper){
        ArrayList<Deed> deeds = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DEEDS_TABLE_NAME + " WHERE complete = ?", new String[]{"done"});
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DEEDS_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_NAME));
            String date = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_DATE));
            String details = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_DETAILS));
            String complete = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_COMPLETE));
            Deed deed = new Deed(id, name, date, details, complete);
            deeds.add(deed);
        }
        cursor.close();
        return deeds;
    }

    public static ArrayList<Deed> allNotComplete(DBHelper dbHelper){
        ArrayList<Deed> deeds = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DEEDS_TABLE_NAME + " WHERE complete = ?", new String[]{"not done"});
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DEEDS_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_NAME));
            String date = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_DATE));
            String details = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_DETAILS));
            String complete = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_COMPLETE));
            Deed deed = new Deed(id, name, date, details, complete);
            deeds.add(deed);
        }
        cursor.close();
        return deeds;
    }

    public static ArrayList<Deed> getByDate(DBHelper dbHelper, String selectedDate){
        ArrayList<Deed> deeds = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DEEDS_TABLE_NAME + " WHERE date = ?", new String[]{selectedDate});
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DEEDS_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_NAME));
            String date = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_DATE));
            String details = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_DETAILS));
            String complete = cursor.getString(cursor.getColumnIndex(DEEDS_COLUMN_COMPLETE));
            Deed deed = new Deed(id, name, date, details, complete);
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
        String selection = " id= ?";
        String[] values = {id.toString()};
        db.delete(DEEDS_TABLE_NAME, selection, values);
        return true;
    }

    public static ArrayList<Deed> sortByDate(ArrayList<Deed> deedsToSort){
        ArrayList<String> dateList = new ArrayList<String>();
        for (Deed deed : deedsToSort){
            dateList.add(deed.getDate());
        }
        Collections.sort(dateList, new Comparator<String>() {
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            @Override
            public int compare(String date1, String date2) {
                try {
                    return format.parse(date1).compareTo(format.parse(date2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        ArrayList<Deed> sortedDeeds = new ArrayList<>();
        int loopTimes = dateList.size();
        int datePos = 0;
            for (String date : dateList)
            {
                for (Deed deed : deedsToSort){
                    String firstDate = dateList.get(datePos);
                    if (deed.getDate().equals(firstDate)){
                        sortedDeeds.add(deed);
                        if (datePos < ( dateList.size() - 1)){
                            datePos++;
                        }
                    }
                }
            }

        return sortedDeeds;


    }


}
