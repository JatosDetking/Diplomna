package bg.tu_sofia.fpmi.jatos.diplomna.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bg.tu_sofia.fpmi.jatos.diplomna.Class.Terrain;
import bg.tu_sofia.fpmi.jatos.diplomna.Methods.Buffer;
import bg.tu_sofia.fpmi.jatos.diplomna.R;

public class Edit extends AppCompatActivity {

    private EditText editName, editP1, editP2, editP3, editC1, editC2, editC3;

    private Button editButtonEdit, editButtonDeletе, editButtonTR, editButtonWater, editButtonWint;

    private String name;

    private double p1,p2,p3;

    private int c1,c2,c3;

    private boolean ready;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setTitle("Edit Terrain");

        Buffer.editType = "edit";

        editName = findViewById(R.id.editName);
        editP1 = findViewById(R.id.editP1);
        editP2 = findViewById(R.id.editP2);
        editP3 = findViewById(R.id.editP3);
        editC1 = findViewById(R.id.editC1);
        editC2 = findViewById(R.id.editC2);
        editC3 = findViewById(R.id.editC3);

        editButtonEdit = findViewById(R.id.editButtonEdit);
        editButtonDeletе = findViewById(R.id.editButtonDeletе);
        editButtonTR = findViewById(R.id.editButtonTR);
        editButtonWater = findViewById(R.id.editButtonWater);
        editButtonWint = findViewById(R.id.editButtonWint);

        editName.setText( Buffer.terrain.getName());
        editP1.setText(Double.toString( Buffer.terrain.getProbabilitie(0)));
        editP2.setText(Double.toString( Buffer.terrain.getProbabilitie(1)));
        editP3.setText(Double.toString( Buffer.terrain.getProbabilitie(2)));
        editC1.setText(Integer.toString( Buffer.terrain.getCost(0)));
        editC2.setText(Integer.toString( Buffer.terrain.getCost(1)));
        editC3.setText(Integer.toString( Buffer.terrain.getCost(2)));

        ready = false;

        editButtonTR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateT();

                if(ready==true){
                    Intent intent = new Intent(Edit.this, EditListOfTemperatureAndRadiation.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Edit.this,"DB ERR",Toast.LENGTH_SHORT).show();
                }
            }
        });
        editButtonWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateT();

                if(ready==true){
                    Intent intent = new Intent(Edit.this, EditListOfWater.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Edit.this,"DB ERR",Toast.LENGTH_SHORT).show();
                }
            }
        });
        editButtonWint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateT();

                if(ready==true){
                    Intent intent = new Intent(Edit.this, EditListOfWind.class);;
                    startActivity(intent);
                }else {
                    Toast.makeText(Edit.this,"DB ERR",Toast.LENGTH_SHORT).show();
                }
            }
        });
        editButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateT();

                Buffer.mydb.updateTerrain(Edit.this, Buffer.terrain.getId(),Buffer.terrain.getName(),Buffer.terrain.getCosts(),Buffer.terrain.getProbabilities());

                Intent intent = new Intent(Edit.this, MainActivity.class);
                startActivity(intent);
            }
        });
        editButtonDeletе.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buffer.mydb.deleteTerrain( Buffer.terrain.getId());

                Intent intent = new Intent(Edit.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void updateT(){
        try {
            name = editName.getText().toString();
            if (name.isEmpty()){
                editName.setError("Name is empty");
                editName.requestFocus();
            }else {
                try {
                    p1 = Double.parseDouble(editP1.getText().toString());
                    try {
                        p2 = Double.parseDouble(editP2.getText().toString());
                        try {
                            p3 = Double.parseDouble(editP3.getText().toString());
                        } catch (Exception e) {
                            editP3.setError("p3 ERR");
                            editP3.requestFocus();
                        }
                    } catch (Exception e) {
                        editP2.setError("p2 ERR");
                        editP2.requestFocus();
                    }
                } catch (Exception e) {
                    editP1.setError("p1 ERR");
                    editP1.requestFocus();
                }
                if (p1+p2+p3!=1){
                    editP1.setError("p1 ERR");
                    editP1.requestFocus();
                    editP2.setError("p2 ERR");
                    editP2.requestFocus();
                    editP3.setError("p3 ERR");
                    editP3.requestFocus();
                }else {
                    if(p1 == 0){
                        editP1.setError("p1 = 0");
                        editP1.requestFocus();
                    }else {
                        if(p2 == 0){
                            editP2.setError("p2 = 0");
                            editP2.requestFocus();
                        }else {
                            if(p3 == 0){
                                editP3.setError("p3 = 0");
                                editP3.requestFocus();
                            }else {
                                try {
                                    c1 = Integer.parseInt(editC1.getText().toString());
                                    try {
                                        c2 = Integer.parseInt(editC2.getText().toString());
                                        try {
                                            c3 = Integer.parseInt(editC3.getText().toString());
                                            try {
                                                Buffer.terrain.update(name,new int[]{c1, c2, c3},new double[]{p1,p2,p3});
                                                ready = true;
                                            } catch (Exception e) {
                                                Toast.makeText(Edit.this,"DB ERR",Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (Exception e) {
                                            editC3.setError("c3 ERR");
                                            editC3.requestFocus();
                                        }
                                    } catch (Exception e) {
                                        editC2.setError("c2 ERR");
                                        editC2.requestFocus();
                                    }
                                } catch (Exception e) {
                                    editC1.setError("c1 ERR");
                                    editC1.requestFocus();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            editName.setError("Name ERR");
            editName.requestFocus();
        }
    }
}
