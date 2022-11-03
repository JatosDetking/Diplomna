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

public class AddListOfTemperatureAndRadiation extends AppCompatActivity {

    private ProgressBar LTRProgressBar;

    private ListView LTRListTR;

    private FloatingActionButton LTRButtonAdd, LTRButtonNext;

    private ArrayList<String> listtr = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_of_temperature_and_radiation);

        getSupportActionBar().setTitle("List of Temperature and Radiation");

        LTRProgressBar = findViewById(R.id.LTRProgressBar);

        LTRButtonAdd = findViewById(R.id.LTRButtonAdd);
        LTRButtonNext = findViewById(R.id.LTRButtonNext);

        LTRListTR =findViewById(R.id.LTRListTR);

        LTRProgressBar.setProgress(50);

        Buffer.fillListFromDB2(this,Buffer.id);
        Buffer.arrToArrTR(listtr);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listtr);

        LTRListTR.setAdapter((arrayAdapter));

        LTRListTR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AddListOfTemperatureAndRadiation.this,EditTemperatureAndRadiation.class);
                intent.putExtra("editTR", Buffer.tr.get(i));
                startActivity(intent);
            }
        });
        LTRButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddListOfTemperatureAndRadiation.this, AddTemperatureAndRadiation.class);
                startActivity(intent);
            }
        });
        LTRButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddListOfTemperatureAndRadiation.this, AddListOfWater.class);
                startActivity(intent);
            }
        });
    }
}