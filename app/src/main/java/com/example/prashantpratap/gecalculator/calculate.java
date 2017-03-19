package com.example.prashantpratap.gecalculator;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Prashant Pratap on 16-03-2017.
 */

public class calculate extends Activity {

    SQLiteDatabase DB = Add_members.DB;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate);

        DB = openOrCreateDatabase("Members_info", Context.MODE_PRIVATE,null);
        String query = "SELECT SUM(Spent) FROM Members";
        Cursor cursor = DB.rawQuery(query,null);

        cursor.moveToFirst();
        float Sum = cursor.getFloat(0);

        cursor = DB.rawQuery("SELECT COUNT(Spent) FROM Members",null);
        cursor.moveToFirst();
        int size = cursor.getInt(0);

        float ind_expans = Sum/size;

        double ind_expanse = Math.round(ind_expans * 100.0) / 100.0;

        String q = "UPDATE Members SET Expanse = Spent - "+ind_expanse;
        DB.execSQL(q);
        ////////////////////////////////////////////////////////////
        cursor = DB.rawQuery("SELECT Expanse FROM Members",null);
        cursor.moveToFirst();
        float exp = cursor.getFloat(0);
        ////////////////////////////////////////////////////
        TextView t_spent = (TextView)findViewById(R.id.t_spent);
        TextView i_spent = (TextView)findViewById(R.id.i_spent);

        t_spent.setText("Total Spent: "+Sum);
        i_spent.setText("Individual Expanse: "+ind_expanse);


        list = (ListView)findViewById(R.id.listview);

        list.setAdapter(new listview(calculate.this,this));
    }
}
