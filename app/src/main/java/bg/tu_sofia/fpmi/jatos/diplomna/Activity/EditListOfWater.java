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

public class EditListOfWater extends AppCompatActivity {

    private ListView editLWaterListWater;

    private FloatingActionButton editLWaterButtonReady;

    private ArrayList<String> listWa = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list_of_water);

        getSupportActionBar().setTitle("Edit List of High Tide");


        editLWaterButtonReady = findViewById(R.id.editLWaterButtonReady);

        editLWaterListWater =findViewById(R.id.editLWaterListWater);

        Buffer.fillListFromDB3(this,Buffer.terrain.getId());
        Buffer.arrToArrWa(listWa);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listWa);

        editLWaterListWater.setAdapter((arrayAdapter));

        editLWaterListWater.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               Intent intent = new Intent(EditListOfWater.this,EditWater.class);
               intent.putExtra("editWa", Buffer.wa.get(i));
               startActivity(intent);
            }
        });
        editLWaterButtonReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditListOfWater.this,Edit.class);
                startActivity(intent);
            }
        });
    }
}