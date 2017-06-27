package com.example.tomas.vakantieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Tomas on 24-6-2017.
 */

public class VakantieAdapter extends ArrayAdapter <VakantieItem> {

    private Context context;

    public VakantieAdapter(Context context, ArrayList<VakantieItem> schoolvakanties) {
        super(context, 0, schoolvakanties);

        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VakantieItem item = getItem(position);

        if( convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vakantie_list_row, parent, false);
        }

        // Zet de vakantienaam in de Listview
        TextView name = convertView.findViewById(R.id.row_vakantie_id);
        name.setText(item.getName());





       return convertView;
    }
}
