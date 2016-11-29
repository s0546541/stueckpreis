package de.philipplange.stueckpreisrechner_neu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Zutat> zutatenListe;
    private DataSource dataSource;

    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        zutatenListe = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.listView);

        dataSource = new DataSource(this);

        adapter = new CustomZutatenAdapter(this, (ArrayList<Zutat>) zutatenListe);
        listView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Zutat hinzugefuegt", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                /*dataSource.open();
                dataSource.addZutat(new Zutat("Butter", 250, Einheit.G, 0.99));
                ArrayList<Zutat> neueListe = (ArrayList<Zutat>) dataSource.getAllZutaten();
                zutatenListe.clear();
                zutatenListe.addAll(neueListe);
                dataSource.close();

                ((ArrayAdapter<Zutat>)adapter).notifyDataSetChanged();*/

                Intent intent = new Intent(MainActivity.this, NeueZutatDialog.class);
                startActivity(intent);




            }
        });

        dataSource.open();
        ArrayList<Zutat> neueListe = (ArrayList<Zutat>) dataSource.getAllZutaten();
        zutatenListe.clear();
        zutatenListe.addAll(neueListe);
        dataSource.close();

        ((ArrayAdapter<Zutat>) adapter).notifyDataSetChanged();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
