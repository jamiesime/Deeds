package example.codeclan.com.deeds;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class activity_create extends AppCompatActivity {

    EditText nameText;
    EditText detailsText;
    TextView selectedDate;
    RadioGroup completeButton;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        nameText = (EditText)findViewById(R.id.nameEdit);
        selectedDate = (TextView)findViewById(R.id.selected_date);
        completeButton = (RadioGroup)findViewById(R.id.isComplete);
        detailsText = (EditText)findViewById(R.id.detailEdit);

        Intent intent = getIntent();
        if (intent.hasExtra("deedName")) {
            Bundle extras = intent.getExtras();
            nameText.setText(extras.getString("deedName"));
        }
        if (intent.hasExtra("deedDate")) {
            Bundle extras = intent.getExtras();
            selectedDate.setText(extras.getString("deedDate"));
        }
        if (intent.hasExtra("deedDetails")) {
            Bundle extras = intent.getExtras();
            detailsText.setText(extras.getString("deedDetails"));
        }


    }

    public void saveNewDeed(View button){
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
        deed.save(dbHelper);
        Intent i = new Intent(this, menu_activity.class);
        startActivity(i);
    }

    public void goToDatePicker(View button){
        Intent i = new Intent(this, activity_date.class);
        i.putExtra("deedName", nameText.getText().toString());
        i.putExtra("detailsName", detailsText.getText().toString());
        i.putExtra("nextIntent", "create");
        startActivity(i);
    }


}
