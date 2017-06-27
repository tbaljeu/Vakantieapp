package com.example.tomas.vakantieapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tomas on 26-6-2017.
 */

public class SchoolVakantieTask  extends AsyncTask<String, Void, String> {

    // Call back
    private onVakantieItemAvailable listener = null;

    // Statics
    private static final String TAG = SchoolVakantieTask.class.getSimpleName();
    private static final String urlString = "https://opendata.rijksoverheid.nl/v1/sources/rijksoverheid/infotypes/schoolholidays/schoolyear/2016-2017?output=json";   // Constructor, set listener
    public SchoolVakantieTask(onVakantieItemAvailable listener) {this.listener = listener;}

    @Override
    protected String doInBackground(String... params) {

        InputStream inputStream = null;
        int responsCode = -1;

        // Het resultaat dat we gaan retourneren
        String response = "";

        for(String url : params) {
            Log.i(TAG, "doInBackground " + url);
        }

        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();

            if (!(urlConnection instanceof HttpURLConnection)) {
                // Url
                return null;
            }

            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            responsCode = httpConnection.getResponseCode();

            if (responsCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                // Log.i(TAG, "doInBackground response = " + response);
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "doInBackground MalformedURLEx " + e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e("TAG", "doInBackground IOException " + e.getLocalizedMessage());
            return null;
        }

        return response;
    }

    protected void onPostExecute(String response) {

        Log.i(TAG, "onPostExecute " + response);

        // parse JSON and inform caller
        JSONObject jsonObject;

        try {
            // Top level json object
            jsonObject = new JSONObject(response);

            // Get all users and start looping
            JSONArray vakanties = jsonObject.getJSONArray("content");

            JSONArray vacations  = vakanties.getJSONObject(0).getJSONArray("vacations");

            for(int idx = 0; idx < vacations.length(); idx++) {



                JSONObject vakantie = vacations.getJSONObject(idx);

                String name = vakantie.getString("type");


                JSONArray jarray =vakantie.getJSONArray ("regions");




                VakantieItem p = new VakantieItem();
                p.setName(name);






                // Geef gevonden item terug via de call back
                listener.onVakantieItemAvailable(p);
            }
        } catch( JSONException ex) {
            Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
    }

    //
    // convert InputStream to String
    //
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    // Call back interface
    interface onVakantieItemAvailable {
        void onVakantieItemAvailable(VakantieItem item);
    }


}
