package de.philipplange.stueckpreisrechner_neu;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class NeueZutatDialog extends AppCompatActivity {

    EditText etName;
    EditText etMenge;
    Spinner spinner;
    EditText etPreis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neue_zutat_dialog);

        List<String> list = new ArrayList<>();
        list.add("g");
        list.add("kg");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        etName = (EditText) findViewById(R.id.etNameDia);
        etMenge = (EditText) findViewById(R.id.etMengeDia);
        etPreis = (EditText) findViewById(R.id.etPreisDia);
        spinner = (Spinner) findViewById(R.id.spinnerEinheitDia);


    }
}
