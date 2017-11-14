package example.codeclan.com.deeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import static android.R.attr.id;

public class activity_edit extends AppCompatActivity {

    EditText nameText;
    EditText detailsText;
    TextView selectedDate;
    RadioGroup completeButton;
    DBHelper dbHelper;
    private Integer id;
    private String listMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        nameText = (EditText)findViewById(R.id.nameEdit);
        selectedDate = (TextView)findViewById(R.id.selected_date);
        completeButton = (RadioGroup)findViewById(R.id.isComplete);
        detailsText = (EditText)findViewById(R.id.detailEdit);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        id = extras.getInt("id");
        listMode = extras.getString("listMode");
        nameText.setText(extras.getString("deedName"));
        detailsText.setText(extras.getString("deedDetails"));
        selectedDate.setText(extras.getString("deedDate"));


    }

    public void updateDeed(View button){
        dbHelper = new DBHelper(this);
        String name = nameText.getText().toString();
        String date = selectedDate.getText().toString();
        String complete = "";
        String details = detailsText.getText().toString();
        Integer rb = completeButton.getCheckedRadioButtonId();
        if (rb == findViewById(R.id.done).getId()){
            complete = "done";
        }
        else {
            complete = "not done";
        }
        Deed deed = new Deed(name, date, details, complete);
        deed.update(dbHelper, id);
        Intent i = new Intent(this, activity_list_deeds.class);
        i.putExtra("listMode", listMode);
        startActivity(i);
    }

    public void deleteDeed(View button){
        dbHelper = new DBHelper(this);
        Deed.delete(dbHelper, id);
        Intent i = new Intent(this, activity_list_deeds.class);
        i.putExtra("listMode", listMode);
        startActivity(i);
    }

    public void goToDatePicker(View button){
        Intent i = new Intent(this, activity_date.class);
        i.putExtra("deedName", nameText.getText().toString());
        i.putExtra("deedDetails", detailsText.getText().toString());
        i.putExtra("detailsName", detailsText.getText().toString());
        i.putExtra("id", id);
        i.putExtra("nextIntent", "edit");
        startActivity(i);
    }

}
