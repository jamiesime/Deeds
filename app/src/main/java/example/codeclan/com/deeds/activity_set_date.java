package example.codeclan.com.deeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.R.attr.format;

public class activity_set_date extends AppCompatActivity {

    private DatePicker datePicker;
    private TextView recurTimes;
    private String deedName;
    private String deedDetails;
    private String nextIntent;
    private ArrayList<String> recurringDates;
    private Integer id;
    private String listMode;
    private Integer recurTimesInt;
    private Spinner recurring_dropdown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_date);
        recurringDates = new ArrayList<String>();


        Bundle extras = getIntent().getExtras();
        deedName = extras.getString("deedName");
        deedDetails = extras.getString("deedDetails");
        id = extras.getInt("id");
        nextIntent = extras.getString("nextIntent");
        listMode = extras.getString("listMode");

        recurTimesInt = 0;
        recurTimes = (TextView)findViewById(R.id.recur_times);
        datePicker = (DatePicker)findViewById(R.id.date_picker_recurring);

        recurring_dropdown = (Spinner)findViewById(R.id.recurring_dropdown);
        String[] items = new String[]{"No", "Daily", "Weekly"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        recurring_dropdown.setAdapter(adapter);


    }


    public void setDateRecurring(View button){
        Integer year = datePicker.getYear();
        Integer month = (datePicker.getMonth() + 1);
        Integer day = datePicker.getDayOfMonth();
        String selectedDate = day.toString() + "-" + month.toString() + "-" + year.toString();
        if (!recurring_dropdown.getSelectedItem().equals("No")){
            setRecurringDates(selectedDate);
        }
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
            i.putExtra("recurring", recurring_dropdown.getSelectedItem().toString());
            i.putExtra("recurValue", recurTimesInt);
            if (recurringDates.size() > 0){
                for (int n = 0 ; n < recurringDates.size() ; n++ ){
                    i.putExtra(String.valueOf(n), recurringDates.get(n));
                }
            }
            startActivity(i);
        }
        if (nextIntent.equals("filter")){
            Intent i = new Intent(this, activity_list_deeds.class);
            i.putExtra("listMode", listMode);
            i.putExtra("selectedDate", selectedDate);
            startActivity(i);
        }

    }

    public ArrayList<String> setRecurringDates(String selectedDate){
        int increment = 0;
        if (recurring_dropdown.getSelectedItem().toString().equals("Daily")){
            increment = 1;
        }
        if (recurring_dropdown.getSelectedItem().toString().equals("Weekly")){
            increment = 7;
        }
        for (int i = 0 ; i < recurTimesInt ; i++){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(dateFormat.parse(selectedDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, increment);  // number of days to add
            selectedDate = dateFormat.format(c.getTime());
                recurringDates.add(selectedDate);
        }
        return recurringDates;
    }

    public void onUpArrow(View button){
        if (recurTimesInt < 10){
            recurTimesInt += 1;
        }
        recurTimes.setText("x" + recurTimesInt.toString());
    }


}
