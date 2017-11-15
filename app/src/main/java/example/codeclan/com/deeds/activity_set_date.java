package example.codeclan.com.deeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

public class activity_set_date extends AppCompatActivity {

    private DatePicker datePicker;
    private String deedName;
    private String deedDetails;
    private String nextIntent;
    private Integer id;
    private String listMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_date);


        Bundle extras = getIntent().getExtras();
        deedName = extras.getString("deedName");
        deedDetails = extras.getString("deedDetails");
        id = extras.getInt("id");
        nextIntent = extras.getString("nextIntent");
        listMode = extras.getString("listMode");
        datePicker = (DatePicker)findViewById(R.id.date_picker_recurring);

        Spinner recurring_dropdown = (Spinner)findViewById(R.id.recurring_dropdown);
        String[] items = new String[]{"No", "Daily", "Weekly"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        recurring_dropdown.setAdapter(adapter);


    }


    public void setDateRecurring(View button){
        Integer year = datePicker.getYear();
        Integer month = (datePicker.getMonth() + 1);
        Integer day = datePicker.getDayOfMonth();
        String selectedDate = day.toString() + "-" + month.toString() + "-" + year.toString();
        if (nextIntent.equals("edit")) {
            Intent i = new Intent(this, activity_edit.class);
            i.putExtra("listMode", listMode);
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
            i.putExtra("selectedDate", selectedDate);
            startActivity(i);
        }

    }


}
