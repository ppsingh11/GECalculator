package com.example.prashantpratap.gecalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText TripName,GroupSize;
    Button AddDetails;
    SharedPreferences spf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spf = getSharedPreferences("myshared", Context.MODE_PRIVATE);

        if(spf.getBoolean("Flag",false)) {

            Intent i = new Intent(this,menu.class);
            startActivity(i);

        }

    }

    public void AddNewTrip(View view) {
        TripName = (EditText)findViewById(R.id.tripname);
        GroupSize = (EditText)findViewById(R.id.groupsize);
        AddDetails = (Button)findViewById(R.id.add_details);

        TripName.setVisibility(view.VISIBLE);
        GroupSize.setVisibility(view.VISIBLE);
        AddDetails.setVisibility(view.VISIBLE);


    }


    public void AddDetails(View view)
    {
        String tripName,grpsize;
        int groupSize;

        tripName = TripName.getText().toString();
        grpsize = GroupSize.getText().toString();

        if(tripName.isEmpty() && grpsize.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Enter Trip Name and Group Size",Toast.LENGTH_SHORT).show();
        }

        else if (tripName.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Enter Trip Name", Toast.LENGTH_SHORT).show();
            }


          else if (grpsize.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Enter Group Size", Toast.LENGTH_SHORT).show();
            }
         else {


            SharedPreferences.Editor edt = spf.edit();
            edt.putString("Trip_Name",tripName);
            groupSize = Integer.parseInt(grpsize);
            edt.putInt("Group_Size",groupSize);
            edt.putBoolean("Flag",false);
            edt.commit();

            Intent i = new Intent(this,Add_members.class);
            startActivity(i);



        }

    }



}

