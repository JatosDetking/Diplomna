package bg.tu_sofia.fpmi.jatos.diplomna.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bg.tu_sofia.fpmi.jatos.diplomna.Class.TemperatureAndRadiation;
import bg.tu_sofia.fpmi.jatos.diplomna.Class.Water;
import bg.tu_sofia.fpmi.jatos.diplomna.Methods.Buffer;
import bg.tu_sofia.fpmi.jatos.diplomna.R;

public class EditWater extends AppCompatActivity {

    private EditText editWaterwater;

    private Button editWaterButtonEdit;

    private double w;

    private Water wa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_water);

        getSupportActionBar().setTitle("Edit High Tide");

        editWaterwater = findViewById(R.id.editWaterwater);

        editWaterButtonEdit = findViewById(R.id.editWaterButtonEdit);

        wa = getIntent().getParcelableExtra("editWa");

        editWaterwater.setText(Double.toString(wa.getWater()));

        editWaterButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    w = Double.parseDouble(editWaterwater.getText().toString());
                    try {
                        Buffer.mydb.updateWa(EditWater.this,wa.getId(),w);
                        Intent intent = null;

                        if(Buffer.editType == "add") {
                            intent = new Intent(EditWater.this, AddListOfWater.class);
                        }else if(Buffer.editType == "edit"){
                            intent =new Intent(EditWater.this, EditListOfWater.class);;
                        }
                        startActivity(intent);
                    }catch (Exception e)
                    {
                        Toast.makeText(EditWater.this,"DB ERR",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    editWaterwater.setError("w ERR");
                    editWaterwater.requestFocus();
                }
            }
        });
    }
}