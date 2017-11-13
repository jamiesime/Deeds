package example.codeclan.com.deeds;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class activity_list_deeds extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_deeds);

        dbHelper = new DBHelper(this);
        ArrayList<Deed> deedList = Deed.all(dbHelper);

        DeedListAdapter listAdapter = new DeedListAdapter(this, deedList);
        ListView listView = (ListView)findViewById(R.id.deed_list);
        listView.setAdapter(listAdapter);
    }
}
