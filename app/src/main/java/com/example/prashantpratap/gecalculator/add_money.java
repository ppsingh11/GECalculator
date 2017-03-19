package com.example.prashantpratap.gecalculator;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Prashant Pratap on 15-03-2017.
 */

public class add_money extends Activity {

    Spinner spn;
    String M_Name;
    SQLiteDatabase DB = Add_members.DB;
    ArrayList<String> Values = new ArrayList<String>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmoney);
        spn = (Spinner)findViewById(R.id.spinner);
        DB = openOrCreateDatabase("Members_info", Context.MODE_PRIVATE,null);
        ////////////////////////////////////////////
        String query = "SELECT Name FROM Members";
        Cursor cursor = DB.rawQuery(query,null);
        Values.add("Who is paying ?");


        if (cursor.moveToFirst()) {
            do {
                String Name = cursor.getString(0);

                Values.add(Name);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DB.close();


        ArrayAdapter<String> my = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Values);
        spn.setAdapter(my);

    }


    public void Add_Expance(View view) {
        M_Name = spn.getSelectedItem().toString();
        EditText amount = (EditText)findViewById(R.id.amount);
        String amt = amount.getText().toString();
        if(M_Name.equals("Who is paying ?"))
        {
            Toast.makeText(getApplicationContext(), "Select Nmae of Payee", Toast.LENGTH_SHORT).show();
        }

        else if(amt.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Enter Amount", Toast.LENGTH_SHORT).show();
        }

        else
        {
            DB = openOrCreateDatabase("Members_info", Context.MODE_PRIVATE,null);
          String query = "UPDATE Members SET Spent = Spent + "+amt+" WHERE Name=\""+M_Name+"\"";
           DB.execSQL(query);

            DB.close();

            Toast.makeText(getApplicationContext()," Amount Added", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,menu.class);
           startActivity(i);
        }



    }
}
