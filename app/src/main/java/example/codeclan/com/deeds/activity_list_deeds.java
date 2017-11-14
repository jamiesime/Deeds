package example.codeclan.com.deeds;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class activity_list_deeds extends AppCompatActivity {

    DBHelper dbHelper;
    ArrayList<Deed> deedList;
    String thisList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_deeds);

        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        if (intent.hasExtra("listMode")){
            Bundle extras = intent.getExtras();
            String listType = extras.getString("listMode");
            switch (listType){
                case "done":
                    deedList = Deed.allComplete(dbHelper);
                    thisList = "done";
                    break;
                case "not done":
                    deedList = Deed.allNotComplete(dbHelper);
                    thisList = "not done";
                    break;
                case "date":
                    String selectedDate = extras.getString("selectedDate");
                    deedList = Deed.getByDate(dbHelper, selectedDate);
                    thisList = "date";
                    break;
                case "all":
                    deedList = Deed.all(dbHelper);
                    thisList = "all";
                    break;
            }
        }

        DeedListAdapter listAdapter = new DeedListAdapter(this, deedList);
        ListView listView = (ListView)findViewById(R.id.deed_list);
        listView.setAdapter(listAdapter);
    }

    public void getDeed(View listItem){
        Deed deed = (Deed) listItem.getTag();
        Intent i = new Intent(this, activity_edit.class);
        i.putExtra("listMode", thisList);
        i.putExtra("deedName", deed.getName());
        i.putExtra("deedDate", deed.getDate());
        i.putExtra("deedDetails", deed.getDetails());
        i.putExtra("deedComplete", deed.getComplete());
        i.putExtra("id", deed.getId());
        startActivity(i);
    }
}
