package example.codeclan.com.deeds;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class activity_list_deeds extends nav {

    TextView listTitle;
    DBHelper dbHelper;
    ArrayList<Deed> deedList;
    String thisList;
    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_deeds);

        dbHelper = new DBHelper(this);

        listTitle = (TextView)findViewById(R.id.list_title);

        Intent intent = getIntent();

        if (intent.hasExtra("listMode")){
            Bundle extras = intent.getExtras();
            String listType = extras.getString("listMode");
            switch (listType){
                case "done":
                    deedList = Deed.allComplete(dbHelper);
                    thisList = "done";
                    listTitle.setText("deeds done");
                    break;
                case "not done":
                    deedList = Deed.allNotComplete(dbHelper);
                    thisList = "not done";
                    listTitle.setText("deeds not done");
                    break;
                case "date":
                    selectedDate = extras.getString("selectedDate");
                    deedList = Deed.getByDate(dbHelper, selectedDate);
                    thisList = "date";
                    String newTitle = extras.getString("listTitle").toString();
                    listTitle.setText(newTitle);
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
        i.putExtra("listTitle", listTitle.getText().toString());
        i.putExtra("deedName", deed.getName());
        i.putExtra("deedDate", deed.getDate());
        i.putExtra("selectedDate", selectedDate);
        i.putExtra("deedDetails", deed.getDetails());
        i.putExtra("deedComplete", deed.getComplete());
        i.putExtra("id", deed.getId());
        startActivity(i);
    }
}
