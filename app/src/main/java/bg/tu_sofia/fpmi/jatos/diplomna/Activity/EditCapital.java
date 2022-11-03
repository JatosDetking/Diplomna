package bg.tu_sofia.fpmi.jatos.diplomna.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bg.tu_sofia.fpmi.jatos.diplomna.Data.PrefConfig;
import bg.tu_sofia.fpmi.jatos.diplomna.Methods.Buffer;
import bg.tu_sofia.fpmi.jatos.diplomna.R;

public class EditCapital extends AppCompatActivity {

    private EditText editCapitalValue;

    private Button editCapitalButtonEdit;

    private int capitalV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_capital);

        getSupportActionBar().setTitle("Edit Capital");

        editCapitalValue = findViewById(R.id.editCapitalValue);

        editCapitalButtonEdit=findViewById(R.id.editCapitalButtonEdit);

        capitalV = PrefConfig.loadTotalFromPref(this);

        editCapitalValue.setText(Integer.toString(capitalV));

        editCapitalButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    capitalV = Integer.parseInt(editCapitalValue.getText().toString());
                    try {
                        PrefConfig.saveTotalInPref(getApplicationContext(),capitalV);

                        Intent intent = new Intent(EditCapital.this, MainActivity.class);
                        startActivity(intent);
                    }catch (Exception e)
                    {
                        Toast.makeText(EditCapital.this,"DB ERR",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    editCapitalValue.setError("c ERR");
                    editCapitalValue.requestFocus();
                }
            }
        });
    }
}