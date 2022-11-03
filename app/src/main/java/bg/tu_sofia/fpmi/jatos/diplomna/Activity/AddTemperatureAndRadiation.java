package bg.tu_sofia.fpmi.jatos.diplomna.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bg.tu_sofia.fpmi.jatos.diplomna.Methods.Buffer;
import bg.tu_sofia.fpmi.jatos.diplomna.R;

public class AddTemperatureAndRadiation extends AppCompatActivity {

    private EditText addTRtemeprature , addTRrad;

    private Button addTRButtonAdd;

    private double t,r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_temperature_and_radiation);

        getSupportActionBar().setTitle("Add Temperature and Radiation");

        addTRtemeprature = findViewById(R.id.addTRtemeprature);
        addTRrad = findViewById(R.id.addTRradiation);

        addTRButtonAdd = findViewById(R.id.addTRButtonAdd);

        addTRButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    t = Double.parseDouble(addTRtemeprature.getText().toString());
                    try {
                        r = Double.parseDouble(addTRrad.getText().toString());
                        try{
                            Buffer.mydb.addTR(AddTemperatureAndRadiation.this,Buffer.id,t,r);

                            Intent intent = new Intent(AddTemperatureAndRadiation.this, AddListOfTemperatureAndRadiation.class);
                            startActivity(intent);
                        }catch (Exception e){
                            Toast.makeText(AddTemperatureAndRadiation.this,"DB ERR",Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        addTRtemeprature.setError("r ERR");
                        addTRtemeprature.requestFocus();
                    }
                }catch (Exception e){
                    addTRtemeprature.setError("t ERR");
                    addTRtemeprature.requestFocus();
                }
            }
        });
    }
}