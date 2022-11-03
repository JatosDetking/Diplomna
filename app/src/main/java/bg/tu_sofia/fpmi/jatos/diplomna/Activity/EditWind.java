package bg.tu_sofia.fpmi.jatos.diplomna.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bg.tu_sofia.fpmi.jatos.diplomna.Class.Water;
import bg.tu_sofia.fpmi.jatos.diplomna.Class.Wind;
import bg.tu_sofia.fpmi.jatos.diplomna.Methods.Buffer;
import bg.tu_sofia.fpmi.jatos.diplomna.R;

public class EditWind extends AppCompatActivity {

    private EditText editWindwind;

    private Button editWindButtonEdit;

    private double w;

    private Wind wi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wind);

        getSupportActionBar().setTitle("Edit Wind Speed");

        editWindwind = findViewById(R.id.editWindwind);

        editWindButtonEdit = findViewById(R.id.editWindButtonEdit);

        wi = getIntent().getParcelableExtra("editWi");

        editWindwind.setText(Double.toString(wi.getWind()));

        editWindButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    w = Double.parseDouble(editWindwind.getText().toString());
                    try {
                        Buffer.mydb.updateWi(EditWind.this,wi.getId(),w);
                        Intent intent = null;

                        if(Buffer.editType == "add") {
                            intent =  new Intent(EditWind.this, AddListOfWind.class);
                        }else if(Buffer.editType == "edit"){
                            intent =new Intent(EditWind.this, EditListOfWind.class);;
                        }
                        startActivity(intent);
                    }catch (Exception e)
                    {
                        Toast.makeText(EditWind.this,"DB ERR",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    editWindwind.setError("w ERR");
                    editWindwind.requestFocus();
                }
            }
        });
    }
}