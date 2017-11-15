package example.codeclan.com.deeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Date;

import static android.R.attr.id;

public class activity_date extends nav {

    private DatePicker datePicker;
    private String deedName;
    private String deedDetails;
    private String nextIntent;
    private Integer id;
    private String listMode;
    private String listTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        Bundle extras = getIntent().getExtras();
        deedName = extras.getString("deedName");
        deedDetails = extras.getString("deedDetails");
        id = extras.getInt("id");
        nextIntent = extras.getString("nextIntent");
        listMode = extras.getString("listMode");
        listTitle = extras.getString("listTitle");



        datePicker = (DatePicker)findViewById(R.id.date_picker);
    }

    public void setDate(View button){
        Integer year = datePicker.getYear();
        Integer month = (datePicker.getMonth() + 1);
        Integer day = datePicker.getDayOfMonth();
        String selectedDate = day.toString() + "-" + month.toString() + "-" + year.toString();
        if (nextIntent.equals("edit")) {
            Intent i = new Intent(this, activity_edit.class);
            i.putExtra("listMode", listMode);
            i.putExtra("listTitle", listTitle);
            i.putExtra("deedDate", selectedDate);
            i.putExtra("deedName", deedName);
            i.putExtra("deedDetails", deedDetails);
            i.putExtra("selectedDate", selectedDate);
            i.putExtra("id", id);
            startActivity(i);
        }
        if (nextIntent.equals("create")){
            Intent i = new Intent(this, activity_create.class);
            i.putExtra("deedDate", selectedDate);
            i.putExtra("deedName", deedName);
            i.putExtra("deedDetails", deedDetails);
            startActivity(i);
        }
        if (nextIntent.equals("filter")){
            Intent i = new Intent(this, activity_list_deeds.class);
            i.putExtra("listMode", listMode);
            i.putExtra("listTitle", "deeds on " + selectedDate);
            i.putExtra("selectedDate", selectedDate);
            startActivity(i);
        }

    }
}
