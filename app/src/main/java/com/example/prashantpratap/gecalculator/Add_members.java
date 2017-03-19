package com.example.prashantpratap.gecalculator;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Prashant Pratap on 15-03-2017.
 */

public class Add_members extends Activity {

    EditText member_name;
    int grp_size;
    int i=1;
    public static SQLiteDatabase DB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_members);
        SharedPreferences spf = getSharedPreferences("myshared", Context.MODE_PRIVATE);
        String t_name = spf.getString("Trip_Name","No Name");
        grp_size = spf.getInt("Group_Size",0);
        TextView TripName = (TextView)findViewById(R.id.trip);
        TripName.setText(TripName.getText().toString()+t_name);
         member_name = (EditText)findViewById(R.id.member_name);
         DB = openOrCreateDatabase("Members_info",Context.MODE_PRIVATE,null);
        DB.execSQL("DROP TABLE IF EXISTS Members");
        DB.execSQL("create table if not exists Members (Name Varchar(20),Spent FLOAT,Expanse FLOAT, PRIMARY KEY(Name))");

    }



    public void AddMembers(View view) {

      if(i<=grp_size)
      {

         String member =  member_name.getText().toString();
          if(member.isEmpty()) {
              Toast.makeText(getApplicationContext(), "Enter Member Name", Toast.LENGTH_SHORT).show();
          }
          else {

              ContentValues values = new ContentValues();
              values.put("Name",member);
              values.put("Spent",0);
              values.put("Expanse",0);
              long clm = DB.insert("Members",null,values);



              if(clm==-1)
              {
                  Toast.makeText(getApplicationContext(),"This Name is Already Added", Toast.LENGTH_SHORT).show();
              }
              else
              {
                  Toast.makeText(getApplicationContext(),member+" Added", Toast.LENGTH_SHORT).show();
                  i++;
                  if(i<=grp_size)
                  {
                      member_name.setText("");
                      member_name.setHint(i + ". Member Name");
                  }

                  else
                  {
                      DB.close();
                      SharedPreferences spf = getSharedPreferences("myshared", Context.MODE_PRIVATE);
                      SharedPreferences.Editor edt = spf.edit();
                      edt.putBoolean("Flag",true);
                      edt.commit();

                      Intent i = new Intent(this,menu.class);
                      startActivity(i);
                  }
              }

          }
      }
    }
}
