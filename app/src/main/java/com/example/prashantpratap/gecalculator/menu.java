package com.example.prashantpratap.gecalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;

/**
 * Created by Prashant Pratap on 15-03-2017.
 */

public class menu extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void Add_Money(View view) {
        Intent i = new Intent(this,add_money.class);
        startActivity(i);
    }

    public void Calculate(View view) {
        Intent i = new Intent(this,calculate.class);
        startActivity(i);
    }

    public void End_trip(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setTitle("End Trip !!!")
                .setMessage("Are you sure you want to end this trip?");
                alert.setCancelable(true);

        alert.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferences spf = getSharedPreferences("myshared", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edt = spf.edit();
                        edt.putBoolean("Flag",false);
                        edt.commit();
                       // Intent i = new Intent(this,Add_members.class);
                       // startActivity(i);
                     //   int p = android.os.Process.myPid();
                     //   android.os.Process.killProcess(p);
                        finishAffinity();
                        System.exit(0);


                        dialog.cancel();
                    }
                });

        alert.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = alert.create();
        alert11.show();



    }

    public void About_us(View view) {

        Intent i = new Intent(this,aboutus.class);
        startActivity(i);
    }
}
