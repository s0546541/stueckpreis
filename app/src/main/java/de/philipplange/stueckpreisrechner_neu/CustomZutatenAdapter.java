package de.philipplange.stueckpreisrechner_neu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class CustomZutatenAdapter extends ArrayAdapter<Zutat> {

    public CustomZutatenAdapter(Context context, ArrayList<Zutat> zutatenListe) {
        super(context, R.layout.custom_row_zutat, zutatenListe);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row_zutat, parent, false);

        Zutat zutat = getItem(position);
        TextView tvName = (TextView) customView.findViewById(R.id.tv_name);
        TextView tvMenge = (TextView) customView.findViewById(R.id.tv_menge);
        TextView tvEinheit = (TextView) customView.findViewById(R.id.tv_einheit);
        TextView tvPreis = (TextView) customView.findViewById(R.id.tv_preis);

        tvName.setText(zutat.getName());
        tvMenge.setText(String.valueOf(zutat.getMenge()));
        tvEinheit.setText(zutat.getEinheit().toString().toLowerCase());
        tvPreis.setText(String.valueOf(zutat.getPreis()) + "€");

        return customView;
    }

    public void swapItems(ArrayList<Zutat> arrayList) {
        this.clear();
        this.addAll(arrayList);
    }
}
