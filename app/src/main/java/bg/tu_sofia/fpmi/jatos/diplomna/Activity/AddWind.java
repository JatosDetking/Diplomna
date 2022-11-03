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

public class AddWind extends AppCompatActivity {

    private EditText addWindwind;

    private Button addWindButtonAdd;

    private double w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wind);

        getSupportActionBar().setTitle("Add Wind Speed");

        addWindwind = findViewById(R.id.addWindwind);

        addWindButtonAdd = findViewById(R.id.addWindButtonAdd);

        addWindButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    w = Double.parseDouble(addWindwind.getText().toString());
                    try {
                        Buffer.mydb.addWi(AddWind.this, Buffer.id, w);

                        Intent intent = new Intent(AddWind.this, AddListOfWind.class);
                        startActivity(intent);
                    }catch (Exception e)
                    {
                        Toast.makeText(AddWind.this,"DB ERR",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    addWindwind.setError("w ERR");
                    addWindwind.requestFocus();
                }
            }
        });
    }
}