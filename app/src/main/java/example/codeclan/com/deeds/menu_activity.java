package example.codeclan.com.deeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class menu_activity extends nav {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activity);

        dbHelper = new DBHelper(this);

    }

    public void toNew(View button){
        Intent i = new Intent(this, activity_create.class);
        startActivity(i);
    }

    public void toView(View button){
        Intent i = new Intent(this, activity_list_deeds.class);
        i.putExtra("listMode", "all");
        startActivity(i);
    }

    public void toFilter(View button){
        Intent i = new Intent(this, activity_filter.class);
        startActivity(i);
    }

    public void toInfo(View button){
        Intent i = new Intent(this, activity_info.class);
        startActivity(i);
    }

    public void toDeedsToday(View button){
        Calendar today = Calendar.getInstance();
        Integer year = today.get(Calendar.YEAR);
        Integer month = (today.get(Calendar.MONTH) + 1);
        Integer day = today.get(Calendar.DAY_OF_MONTH);
        String selectedDate = day.toString() + "-" + month.toString() + "-" + year.toString();
        Intent i = new Intent(this, activity_list_deeds.class);
        i.putExtra("listMode", "date");
        i.putExtra("selectedDate", selectedDate);
        startActivity(i);
    }
}
