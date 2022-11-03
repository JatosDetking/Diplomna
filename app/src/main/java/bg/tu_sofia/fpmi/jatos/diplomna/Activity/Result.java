package bg.tu_sofia.fpmi.jatos.diplomna.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import bg.tu_sofia.fpmi.jatos.diplomna.R;

public class Result extends AppCompatActivity {
    private TextView resultTextView;
    private ListView resultListТerrain;

    private String title;
    private String[] result;


    private ArrayList<String> resultA = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSupportActionBar().setTitle("Result");

        resultTextView = findViewById(R.id.resultTextView);
        resultListТerrain =findViewById(R.id.resultListТerrain);

        title = getIntent().getStringExtra("title");
        result = getIntent().getStringArrayExtra("result");

        resultTextView.setText(title);

        for (int i = 0; i < result.length; i++) {
            resultA.add(result[i]);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,resultA);

        resultListТerrain.setAdapter((arrayAdapter));
        resultListТerrain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}