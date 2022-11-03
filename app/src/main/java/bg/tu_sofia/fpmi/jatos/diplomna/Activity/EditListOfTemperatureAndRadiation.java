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

public class EditListOfTemperatureAndRadiation extends AppCompatActivity {

    private ListView editLTRListTR;

    private FloatingActionButton editLTRButtonReady;

    private ArrayList<String> listtr = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list_of_temperature_and_radiation);

        getSupportActionBar().setTitle("Edit List of Temperature and Radiation");

        editLTRButtonReady = findViewById(R.id.editLTRButtonReady);

        editLTRListTR =findViewById(R.id.editLTRListTR);

        Buffer.fillListFromDB2(this,Buffer.terrain.getId());
        Buffer.arrToArrTR(listtr);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listtr);

        editLTRListTR.setAdapter((arrayAdapter));

        editLTRListTR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(EditListOfTemperatureAndRadiation.this,EditTemperatureAndRadiation.class);
                intent.putExtra("editTR", Buffer.tr.get(i));
                startActivity(intent);
            }
        });
        editLTRButtonReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(EditListOfTemperatureAndRadiation.this,Edit.class);
                 startActivity(intent);
            }
        });
    }
}