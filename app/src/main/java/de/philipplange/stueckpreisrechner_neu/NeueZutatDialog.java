package de.philipplange.stueckpreisrechner_neu;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


public class NeueZutatDialog extends AppCompatActivity {

    EditText etName;
    EditText etMenge;
    Spinner spinner;
    EditText etPreis;
    Button btnAbbrechen;
    Button btnHinzufuegen;

    private DataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neue_zutat_dialog);


        etName = (EditText) findViewById(R.id.etNameDia);
        etMenge = (EditText) findViewById(R.id.etMengeDia);
        etPreis = (EditText) findViewById(R.id.etPreisDia);
        spinner = (Spinner) findViewById(R.id.spinnerEinheitDia);
        btnAbbrechen = (Button) findViewById(R.id.btnAbbrechenDia);
        btnHinzufuegen = (Button) findViewById(R.id.btnHinzufuegenDia);

        dataSource = new DataSource(this);

        // Befuellen des Spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.einheiten_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnAbbrechen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnHinzufuegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!allesAusgefuellt()) {
                    Snackbar.make(view, "Bitte alle Felder ausfüllen.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    if (enthaelt(etName.getText().toString()))
                        Snackbar.make(view, "Zutat mit dem Namen existiert bereits.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    else {
                        dataSource.open();
                        dataSource.addZutat(neueZutatAusDialog());
                        dataSource.close();
                        finish();
                    }
                }
            }
        });


    }

    private Zutat neueZutatAusDialog() {
        Zutat zutat = new Zutat(
                etName.getText().toString(),
                Double.parseDouble(etMenge.getText().toString()),
                spinnerAuslesen(),
                Double.parseDouble(etPreis.getText().toString()));

        return zutat;
    }

    private boolean allesAusgefuellt() {
        return !(etName.getText().toString().isEmpty() ||
                etMenge.getText().toString().isEmpty() ||
                etPreis.getText().toString().isEmpty());
    }

    private Einheit spinnerAuslesen() {
        switch (spinner.getSelectedItemPosition()) {
            case 0:
                return Einheit.KG;

            case 1:
                return Einheit.G;

            case 2:
                return Einheit.L;

            case 3:
                return Einheit.ML;

            case 4:
                return Einheit.STUECK;

            case 5:
                return Einheit.TUETE;

            default:
                return null;

        }


    }

    /**
     * prueft, ob in der Datenbank eine Zutat mit dem Namen existiert.
     *
     * @param name Name der Zutat, der gesucht werden soll.
     * @return true, wenn der Name bereits in der Datenbank steht.
     */
    private boolean enthaelt(String name) {
        boolean enthalten = false;
        dataSource.open();
        ArrayList<Zutat> neueListe = (ArrayList<Zutat>) dataSource.getAllZutaten();
        for (Zutat zutat : neueListe) {
            if (zutat.getName().equalsIgnoreCase(name))
                enthalten = true;
        }
        dataSource.close();
        return enthalten;
    }

}
