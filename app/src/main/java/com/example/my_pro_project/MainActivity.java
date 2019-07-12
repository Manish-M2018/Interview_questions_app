package com.example.my_pro_project;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button bsimple, btough, bseeotherapps, brateapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding the title bar
        LinearLayout frontpage_ll = (LinearLayout) findViewById(R.id.front_page_title_bar_page);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.frontpage_title_bar);


        bsimple = (Button) findViewById(R.id.bsq);
        btough = (Button) findViewById(R.id.btq);
        bseeotherapps = (Button) findViewById(R.id.bseeotherapps);
        brateapp = (Button) findViewById(R.id.rate);

        bsimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Simple_questions.class);
                startActivity(i);
            }
        });

        btough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Tough_questions.class);
                startActivity(i);
            }
        });

        bseeotherapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Uri uri1 = Uri.parse("market://search?q=Manish_M");
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goToMarket1);
                }
                catch (ActivityNotFoundException e){
                    Uri uri1 = Uri.parse("https:play.google.com/store/search?q=Manish_M");
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goToMarket1);
                }
            }
        });

        brateapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri1 = Uri.parse("market://details?id=" + getPackageName());
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goToMarket1);
                }
                catch (ActivityNotFoundException e){
                    Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
                    Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                    startActivity(goToMarket1);
                }
            }
        });
    }
}
