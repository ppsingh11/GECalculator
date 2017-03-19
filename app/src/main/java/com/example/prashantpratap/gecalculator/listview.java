package com.example.prashantpratap.gecalculator;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Pair;
import android.util.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Attributes;

import static android.content.Context.*;
import static java.lang.StrictMath.abs;

/**
 * Created by Prashant Pratap on 16-03-2017.
 */

public class listview extends BaseAdapter {



    calculate cal;
    SQLiteDatabase DB = Add_members.DB;
    int i=1;
    TextView NAME,Expanse,Balance;


    ArrayList<String>member_name = new ArrayList<String>();
    ArrayList<Float>member_spent = new ArrayList<Float>();
    ArrayList<Float>member_expanse = new ArrayList<Float>();
    ArrayList<Float>member_expanse_copy = new ArrayList<Float>();


  ArrayList  <ArrayList <Pair <String,Float>>> Val = new ArrayList <ArrayList <Pair <String,Float>>>();

    Context mContext;

    listview(calculate c,Context context) {
        cal = c;
        mContext = context;

            DB= mContext.openOrCreateDatabase("Members_info",Context.MODE_PRIVATE, null);


              String query = "SELECT * FROM Members ORDER BY Expanse DESC";
            Cursor cursor = DB.rawQuery(query,null);


            if (cursor.moveToFirst()) {
                do {
                    String Name = cursor.getString(0);
                    Float spent = cursor.getFloat(1);
                    Float expanse = cursor.getFloat(2);
                    member_name.add(Name);
                    member_spent.add(spent);
                    member_expanse.add(expanse);
                    member_expanse_copy.add(expanse);
                }  while (cursor.moveToNext());
            }

            cursor.close();
          DB.close();



        ////////////////////////////////////////////////////////

        for(int p=0;p<member_name.size();p++)
        {
            Val.add(new ArrayList <Pair <String,Float>>());
        }

        //////////////////////////////////////////////////////

        ////////////// Algo for Calculation  /////////////////////////////////


        int var1 =0,var2=member_name.size()-1;

        while(var1 < var2)
        {
            if(member_expanse.get(var1)+member_expanse.get(var2)==0)
            {
                Pair p = new Pair(member_name.get(var2),member_expanse.get(var1));
                Val.get(var1).add(p);



                Pair p1 = new Pair(member_name.get(var1),member_expanse.get(var1));
                Val.get(var2).add(p1);

                var1++;
                var2--;

            }

            else if(member_expanse.get(var1) < abs(member_expanse.get(var2)))
            {
                Pair p = new Pair(member_name.get(var2),member_expanse.get(var1));
                Val.get(var1).add(p);


                Float temp =member_expanse.get(var2) + member_expanse.get(var1);

                member_expanse.set(var2,temp);


                Pair p1 = new Pair(member_name.get(var1),member_expanse.get(var1));
                Val.get(var2).add(p1);
                var1++;

            }


            else if(member_expanse.get(var1) > abs(member_expanse.get(var2)))
            {
                Pair p = new Pair(member_name.get(var2),abs(member_expanse.get(var2)));
                Val.get(var1).add(p);


                Float temp =member_expanse.get(var1) + member_expanse.get(var2);

                member_expanse.set(var1,temp);


                Pair p1 = new Pair(member_name.get(var1),abs(member_expanse.get(var2)));
                Val.get(var2).add(p1);

                var2--;

            }
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inf = LayoutInflater.from(cal);
        View v = inf.inflate(R.layout.listview,null);
        ListView listview = (ListView)v.findViewById(R.id.list);
        /////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////
        NAME = (TextView)v.findViewById(R.id.Name);
        Expanse = (TextView)v.findViewById(R.id.expanse);
        Balance =(TextView)v.findViewById(R.id.balance);



         NAME.setText("Name: "+member_name.get(position));
        Expanse.setText("Spent: "+member_spent.get(position));
        Balance.setText("Balance: "+member_expanse_copy.get(position));


        ArrayList<String> inner = new ArrayList<String>();

/////////////////////////////////////////////////////////////////////////////////////////////////////


        ArrayList<Pair<String,Float> > X = Val.get(position);


        for(int k=0;k<X.size();k++)
        {

            Pair<String,Float> X1 = X.get(k);

            String str = X1.first;
            Float flt = X1.second;

            if(member_expanse_copy.get(position)<0)
            {
                String string = "Pay "+flt+" to "+str;
                inner.add(string);
            }

            else
            {
                String string = "Get "+flt+" from "+str;
                inner.add(string);
            }
        }

        ArrayAdapter<String> my = new ArrayAdapter<String>(cal,android.R.layout.simple_dropdown_item_1line,inner);

        listview.setAdapter(my);

        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = 100* (my.getCount());

        listview.setLayoutParams(params);
        listview.requestLayout();


        return v;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return member_name.size();
    }
}
