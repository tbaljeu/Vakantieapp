package com.example.tomas.vakantieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.tomas.vakantieapp.R.id.row_vakantie_id;

public class MainActivity extends AppCompatActivity  implements SchoolVakantieTask.onVakantieItemAvailable, ListView.OnItemClickListener{

    //ArrayList aanmaken
    private ArrayList<VakantieItem> schoolvakanties = new ArrayList<>();
    private ArrayList<Tijdvak> tijdvak = new ArrayList<Tijdvak>();

    //ListView aanmaken
    private ListView schoolvakantieListView;

    //Adapter aanmaken
    private VakantieAdapter mVakantieAdapter;


    //Statics
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schoolvakantieListView = (ListView) findViewById(R.id.vakantieListView);

        mVakantieAdapter = new VakantieAdapter(this,schoolvakanties);

        schoolvakantieListView.setAdapter(mVakantieAdapter);

        schoolvakantieListView.setOnItemClickListener(this);

        mVakantieAdapter.notifyDataSetChanged();

        SchoolVakantieTask task = new SchoolVakantieTask(this);
        String[] urls = new String[]
                {};
        task.execute(urls);
/**
        // #tijdvak 1,2,3 dummies
        Tijdvak tv1 = new Tijdvak();
        Tijdvak tv2 = new Tijdvak();
        Tijdvak tv3 = new Tijdvak();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            tv1.startdate = dateFormat.parse("2015-10-16T22:00:00.000Z");
            tv1.enddate = dateFormat.parse("2015-11-16T22:00:00.000Z");
            tv1.region = "zuid";
            tijdvak.add(tv1);
            tv2.startdate = dateFormat.parse("2016-12-16T22:00:00.000Z");
            tv2.enddate = dateFormat.parse("2017-01-16T22:00:00.000Z");
            tv2.region = "zuid";
            tijdvak.add(tv2);
            tv3.startdate = dateFormat.parse("2017-11-16T22:00:00.000Z");
            tv3.enddate = dateFormat.parse("2018-04-16T22:00:00.000Z");
            tv3.region = "Noord";
            tijdvak.add(tv3);
        } catch (ParseException ex) {
            Log.e(TAG, ex.getLocalizedMessage());
        }

        VakantieItem vak1 = new VakantieItem();
        vak1.name = "Voorjaarsvakantie";
        vak1.compulsorydates = true;
        vak1.tijdvak.add(tv1);
        schoolvakanties.add(vak1);

        VakantieItem vak2 = new VakantieItem();
        vak2.name = "Najaarsvakantie";
        vak2.compulsorydates = true;
        vak2.tijdvak.add(tv2);
        schoolvakanties.add(vak2);

        VakantieItem vak3 = new VakantieItem();
        vak3.name = "Herfstvakantie";
        vak3.compulsorydates = true;
        vak3.tijdvak.add(tv3);
        schoolvakanties.add(vak3);
**/
    }
    @Override
   public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

        Intent intent = new Intent(getApplicationContext(), VakantieSpreiding.class);
        VakantieItem item = (VakantieItem) this.schoolvakanties.get(i);
        Tijdvak vak =(Tijdvak) this.tijdvak.get(i);
        intent.putExtra("schoolvakantieitem", item);
        intent.putExtra("Tijdvakken",vak);
        startActivity(intent);
    }

    @Override
    public void onVakantieItemAvailable(VakantieItem item) {
        // Opslaag in array of mss wel in db?
        schoolvakanties.add(item);
        mVakantieAdapter.notifyDataSetChanged();
    }

}
