package example.codeclan.com.deeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Date;

public class activity_date extends AppCompatActivity {

    private DatePicker datePicker;
    private String deedName;
    private String deedDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        Bundle extras = getIntent().getExtras();
        deedName = extras.getString("deedName");
        deedDetails = extras.getString("deedDetails");


        datePicker = (DatePicker)findViewById(R.id.date_picker);
    }

    public void setDate(View button){
        Integer year = datePicker.getYear();
        Integer month = (datePicker.getMonth() + 1);
        Integer day = datePicker.getDayOfMonth();
        String selectedDate = day.toString() + "-" + month.toString() + "-" + year.toString();
        Intent i = new Intent(this, activity_create.class);
        i.putExtra("dateInput", selectedDate);
        i.putExtra("deedName", deedName);
        i.putExtra("deedDetails", deedDetails);
        startActivity(i);
    }
}
