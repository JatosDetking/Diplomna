package bg.tu_sofia.fpmi.jatos.diplomna.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import bg.tu_sofia.fpmi.jatos.diplomna.Methods.Buffer;
import bg.tu_sofia.fpmi.jatos.diplomna.R;

public class Add extends AppCompatActivity {
    private EditText addName, addP1, addP2, addP3, addC1, addC2, addC3;

    private Button addButtonNext;

    private ProgressBar addProgressBar;

    private String name;

    private int c1,c2,c3;

    private double p1,p2,p3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setTitle("Add Terrain");

        Buffer.editType = "add";

        addName = findViewById(R.id.addName);
        addP1 = findViewById(R.id.addP1);
        addP2 = findViewById(R.id.addP2);
        addP3 = findViewById(R.id.addP3);
        addC1 = findViewById(R.id.addC1);
        addC2 = findViewById(R.id.addC2);
        addC3 = findViewById(R.id.addC3);

        addButtonNext = findViewById(R.id.addButtonNext);

        addProgressBar = findViewById(R.id.addProgressBar);

        addProgressBar.setProgress(25);

        addButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    name = addName.getText().toString();
                    if (name.isEmpty()){
                        addName.setError("Name is empty");
                        addName.requestFocus();
                    }else {
                        try {
                            p1 = Double.parseDouble(addP1.getText().toString());
                            try {
                                p2 = Double.parseDouble(addP2.getText().toString());
                                try {
                                    p3 = Double.parseDouble(addP3.getText().toString());
                                } catch (Exception e) {
                                    addP3.setError("p3 ERR");
                                    addP3.requestFocus();
                                }
                            } catch (Exception e) {
                                addP2.setError("p2 ERR");
                                addP2.requestFocus();
                            }
                        } catch (Exception e) {
                            addP1.setError("p1 ERR");
                            addP1.requestFocus();
                        }
                        if (p1+p2+p3!=1){
                            addP1.setError("p1 ERR");
                            addP1.requestFocus();
                            addP2.setError("p2 ERR");
                            addP2.requestFocus();
                            addP3.setError("p3 ERR");
                            addP3.requestFocus();
                        }else {
                            if(p1 == 0){
                                addP1.setError("p1 = 0");
                                addP1.requestFocus();
                            }else {
                                if(p2 == 0){
                                    addP1.setError("p2 = 0");
                                    addP1.requestFocus();
                                }else {
                                    if(p3 == 0){
                                        addP1.setError("p3 = 0");
                                        addP1.requestFocus();
                                    }else {
                                        try {
                                            c1 = Integer.parseInt(addC1.getText().toString());
                                            try {
                                                c2 = Integer.parseInt(addC2.getText().toString());
                                                try {
                                                    c3 = Integer.parseInt(addC3.getText().toString());
                                                        try {
                                                            Buffer.id = Buffer.mydb.addTerrain(Add.this,name,new int[]{c1, c2, c3},new double[]{p1,p2,p3});
                                                            Intent intent = new Intent(Add.this, AddListOfTemperatureAndRadiation.class);
                                                            startActivity(intent);
                                                        } catch (Exception e) {
                                                            Toast.makeText(Add.this,"DB ERR",Toast.LENGTH_SHORT).show();
                                                        }
                                                } catch (Exception e) {
                                                    addC3.setError("c3 ERR");
                                                    addC3.requestFocus();
                                                }
                                            } catch (Exception e) {
                                                addC2.setError("c2 ERR");
                                                addC2.requestFocus();
                                            }
                                        } catch (Exception e) {
                                            addC1.setError("c1 ERR");
                                            addC1.requestFocus();
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    addName.setError("Name ERR");
                    addName.requestFocus();
                }
            }
        });
    }
}