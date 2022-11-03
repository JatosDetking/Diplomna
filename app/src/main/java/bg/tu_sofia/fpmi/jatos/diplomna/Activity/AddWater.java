package bg.tu_sofia.fpmi.jatos.diplomna.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bg.tu_sofia.fpmi.jatos.diplomna.Methods.Buffer;
import bg.tu_sofia.fpmi.jatos.diplomna.R;

public class AddWater extends AppCompatActivity {

    private EditText addWaterwater;

    private Button addWaterButtonAdd;

    private double w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_water);

        getSupportActionBar().setTitle("Add Water");

        addWaterwater = findViewById(R.id.addWaterwater);

        addWaterButtonAdd = findViewById(R.id.addWaterButtonAdd);

        addWaterButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    w = Double.parseDouble(addWaterwater.getText().toString());
                    try {
                        Buffer.mydb.addWa(AddWater.this, Buffer.id, w);

                        Intent intent = new Intent(AddWater.this, AddListOfWater.class);
                        startActivity(intent);
                    }catch (Exception e)
                    {
                        Toast.makeText(AddWater.this,"DB ERR",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    addWaterwater.setError("w ERR");
                    addWaterwater.requestFocus();
                }
            }
        });
    }
}