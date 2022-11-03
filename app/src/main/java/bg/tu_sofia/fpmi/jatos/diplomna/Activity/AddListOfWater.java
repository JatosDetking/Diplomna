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

public class AddListOfWater extends AppCompatActivity {

    private ProgressBar LWProgressBar;

    private ListView LWaterListWater;

    private FloatingActionButton LWaterButtonAdd, LWaterButtonNext;

    private ArrayList<String> listWa = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_of_water);

        getSupportActionBar().setTitle("List of High Tide");

        LWProgressBar = findViewById(R.id.LWProgressBar);

        LWaterButtonAdd = findViewById(R.id.LWaterButtonAdd);
        LWaterButtonNext = findViewById(R.id.LWaterButtonNext);

        LWaterListWater =findViewById(R.id.LWaterListWater);

        LWProgressBar.setProgress(75);

        Buffer.fillListFromDB3(this,Buffer.id);
        Buffer.arrToArrWa(listWa);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listWa);

        LWaterListWater.setAdapter((arrayAdapter));

        LWaterListWater.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(AddListOfWater.this,EditWater.class);
                intent.putExtra("editWa", Buffer.wa.get(i));
                startActivity(intent);
            }
        });
        LWaterButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddListOfWater.this, AddWater.class);
                startActivity(intent);
            }
        });
        LWaterButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(AddListOfWater.this, AddListOfWind.class);
               startActivity(intent);
            }
        });
    }
}