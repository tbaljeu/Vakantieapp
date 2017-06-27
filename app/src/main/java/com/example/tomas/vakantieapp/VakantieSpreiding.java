package com.example.tomas.vakantieapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;


/**
 * Created by Tomas on 25-6-2017.
 */

public class VakantieSpreiding extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vakantiespreiding_list);


        Intent intent = getIntent();
        VakantieItem item = (VakantieItem) intent.getSerializableExtra("schoolvakantieitem");
        Tijdvak vak = (Tijdvak) intent.getSerializableExtra("Tijdvakken");

        TextView vakantieView = (TextView) findViewById(R.id.vakantieView);
        vakantieView.setText(item.name);

        TextView regio = (TextView) findViewById(R.id.regioView);
        regio.setText(vak.region);

        TextView begin = (TextView) findViewById ( R.id.beginDate);
        begin.setText("From:"+ vak.startdate.toString());

        TextView eind = (TextView) findViewById (R.id.endDate);
        eind.setText("To:" +vak.enddate.toString());






    }
}
