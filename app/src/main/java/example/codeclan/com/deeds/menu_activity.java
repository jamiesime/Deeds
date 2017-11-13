package example.codeclan.com.deeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class menu_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activity);
    }

    public void toNew(View button){
        Intent i = new Intent(this, activity_create.class);
        startActivity(i);
    }

    public void toView(View button){
        Intent i = new Intent(this, activity_list_deeds.class);
        startActivity(i);
    }
}
