package example.codeclan.com.deeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class activity_filter extends nav {

    private RadioGroup completeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        completeButton = (RadioGroup)findViewById(R.id.is_complete);
    }

    public void byDate(View button){
        Intent i = new Intent(this, activity_date.class);
        i.putExtra("nextIntent", "filter");
        i.putExtra("listMode", "date");
        startActivity(i);
    }

    public void byComplete(View button){
        Intent i = new Intent(this, activity_list_deeds.class);
        String complete = "";
        Integer rb = completeButton.getCheckedRadioButtonId();
        if (rb == findViewById(R.id.done).getId()){
            complete = "done";
        }
        else {
            complete = "not done";
        }
        i.putExtra("listMode", complete);
        startActivity(i);
    }
}
