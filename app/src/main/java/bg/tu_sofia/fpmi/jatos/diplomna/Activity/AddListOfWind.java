package bg.tu_sofia.fpmi.jatos.diplomna.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import bg.tu_sofia.fpmi.jatos.diplomna.Methods.Buffer;
import bg.tu_sofia.fpmi.jatos.diplomna.R;

public class AddListOfWind extends AppCompatActivity {

    private ProgressBar LWindProgressBar;

    private ListView LWindListWind;

    private FloatingActionButton LWindButtonAdd, LWindButtonReady;

    private ArrayList<String> listWi = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_of_wind);

        getSupportActionBar().setTitle("List of Wind Speed");

        LWindProgressBar = findViewById(R.id.LWindProgressBar);

        LWindButtonAdd = findViewById(R.id.LWindButtonAdd);
        LWindButtonReady = findViewById(R.id.LWindButtonReady);

        LWindListWind = findViewById(R.id.LWindListWind);

        LWindProgressBar.setProgress(100);

        Buffer.fillListFromDB4(this,Buffer.id);
        Buffer.arrToArrWi(listWi);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listWi);

        LWindListWind.setAdapter((arrayAdapter));

        LWindListWind.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               Intent intent = new Intent(AddListOfWind.this,EditWind.class);
               intent.putExtra("editWi", Buffer.wi.get(i));
               startActivity(intent);
            }
        });
        LWindButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(AddListOfWind.this, AddWind.class);
               startActivity(intent);
            }
        });
        LWindButtonReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(AddListOfWind.this, MainActivity.class);
               startActivity(intent);
            }
        });
    }
}