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

public class EditListOfWind extends AppCompatActivity {

    private ListView editLWindListWind;

    private FloatingActionButton editLWindButtonReady;

    private ArrayList<String> listWi = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list_of_wind);

        getSupportActionBar().setTitle("Edit List of Wind Speed");

        editLWindButtonReady = findViewById(R.id.editLWindButtonReady);

        editLWindListWind = findViewById(R.id.editLWindListWind);

        Buffer.fillListFromDB4(this,Buffer.terrain.getId());
        Buffer.arrToArrWi(listWi);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listWi);

        editLWindListWind.setAdapter((arrayAdapter));

        editLWindListWind.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(EditListOfWind.this,EditWind.class);
                intent.putExtra("editWi", Buffer.wi.get(i));
                startActivity(intent);
            }
        });
        editLWindButtonReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditListOfWind.this, Edit.class);
                startActivity(intent);
            }
        });
    }
}