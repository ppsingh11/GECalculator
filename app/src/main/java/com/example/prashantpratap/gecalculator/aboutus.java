package com.example.prashantpratap.gecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Prashant Pratap on 17-03-2017.
 */

public class aboutus extends Activity {

    ImageView pps , omi;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);

        pps =(ImageView)findViewById(R.id.pps);
        omi =(ImageView)findViewById(R.id.omi);
        pps.setImageResource(R.drawable.pps);
        omi.setImageResource(R.drawable.omi);


}}
