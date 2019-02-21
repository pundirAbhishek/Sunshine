package com.example.sunshine;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailWeatherActivity extends AppCompatActivity {

    String temp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_weather);

//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        assert bundle != null;
//        temp = bundle.getString("Temperature");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detailed_activity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share_menu_item)
        {
            String title = "Share Intent";
            String type = "text/plain";

            Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                    .setChooserTitle(title)
                    .setType(type)
                    .setText(String.valueOf(temp))
                    .getIntent();

            if (shareIntent.resolveActivity(getPackageManager()) != null){
                startActivity(shareIntent);
            }
        }
        else if(item.getItemId() == R.id.settings_menu_item)
        {
            Intent settingsIntent = new Intent(this,Settings.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
