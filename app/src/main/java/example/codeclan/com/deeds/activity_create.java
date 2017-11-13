package example.codeclan.com.deeds;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.w3c.dom.Text;

public class activity_create extends AppCompatActivity {

    EditText nameText;
    RadioGroup completeButton;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        nameText = (EditText)findViewById(R.id.nameEdit);
        completeButton = (RadioGroup)findViewById(R.id.isComplete);
    }

    public void saveNewDeed(View button){
        dbHelper = new DBHelper(this);
        String name = nameText.getText().toString();
        String complete = "";
        Integer rb = completeButton.getCheckedRadioButtonId();
        if (rb == findViewById(R.id.done).getId()){
            complete = "true";
        }
        else {
            complete = "false";
        }
        Deed deed = new Deed(name, complete);
        deed.save(dbHelper);
        Intent i = new Intent(this, menu_activity.class);
        startActivity(i);
    }


}
