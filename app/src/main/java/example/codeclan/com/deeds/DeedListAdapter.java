package example.codeclan.com.deeds;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 13/11/2017.
 */

public class DeedListAdapter extends ArrayAdapter<Deed> {

    public DeedListAdapter(Context context, ArrayList<Deed> deeds){
        super(context, 0, deeds);
    }

    public View getView(int position, View listItemView, ViewGroup parent){
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.deed_list_item, parent, false);
        }
        Deed currentDeed = getItem(position);
        TextView name = (TextView)listItemView.findViewById(R.id.deed_name);
        name.setText(currentDeed.getName().toString());
        TextView date = (TextView)listItemView.findViewById(R.id.deed_date);
        date.setText(currentDeed.getDate().toString());
        TextView complete = (TextView)listItemView.findViewById(R.id.deed_complete);
        complete.setText(currentDeed.getComplete().toString());


        listItemView.setTag(currentDeed);

        return listItemView;
    }

}
