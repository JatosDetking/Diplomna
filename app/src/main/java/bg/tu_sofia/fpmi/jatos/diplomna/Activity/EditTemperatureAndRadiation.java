package bg.tu_sofia.fpmi.jatos.diplomna.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bg.tu_sofia.fpmi.jatos.diplomna.Class.TemperatureAndRadiation;
import bg.tu_sofia.fpmi.jatos.diplomna.Class.Terrain;
import bg.tu_sofia.fpmi.jatos.diplomna.Methods.Buffer;
import bg.tu_sofia.fpmi.jatos.diplomna.R;

public class EditTemperatureAndRadiation extends AppCompatActivity {

    private EditText editTRtemeprature , editTRradiation;

    private Button editTRButtonEdit;

    private double t,r;

    private TemperatureAndRadiation tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_temperature_and_radiation);


        getSupportActionBar().setTitle("Edit Temperature and Radiation");

        editTRtemeprature = findViewById(R.id.editTRtemeprature);
        editTRradiation = findViewById(R.id.editTRradiation);

        editTRButtonEdit = findViewById(R.id.editTRButtonEdit);

        tr = getIntent().getParcelableExtra("editTR");

        editTRtemeprature.setText(Double.toString(tr.getTemp()));
        editTRradiation.setText(Double.toString(tr.getRad()));

        editTRButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    t = Double.parseDouble(editTRtemeprature.getText().toString());
                    try {
                        r = Double.parseDouble(editTRradiation.getText().toString());
                        try {
                            Buffer.mydb.updateTR(EditTemperatureAndRadiation.this, tr.getId(), t, r);
                            Intent intent = null;

                            if (Buffer.editType == "add") {
                                intent = new Intent(EditTemperatureAndRadiation.this, AddListOfTemperatureAndRadiation.class);
                            } else if (Buffer.editType == "edit") {
                                intent = new Intent(EditTemperatureAndRadiation.this, EditListOfTemperatureAndRadiation.class);
                            }
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(EditTemperatureAndRadiation.this, "DB ERR", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        editTRradiation.setError("r ERR");
                        editTRradiation.requestFocus();
                    }
                } catch (Exception e) {
                    editTRtemeprature.setError("t ERR");
                    editTRtemeprature.requestFocus();
                }
            }
        });
    }
}