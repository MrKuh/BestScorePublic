package project.bestscore.ui.teammates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import project.bestscore.R;

public class TeammateAdd extends AppCompatActivity {

    private EditText edNameOfNewPlayer;
    private EditText edWinsOfNewPlayer;
    private ImageButton btnAddPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teammate_add);

        edNameOfNewPlayer = findViewById(R.id.edNameOfNewPlayer);
        edWinsOfNewPlayer = findViewById(R.id.edWinsOfNewPlayer);
        btnAddPlayer = findViewById(R.id.btnAddPlayer);
        btnAddPlayer.setEnabled(false);

        edNameOfNewPlayer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!edNameOfNewPlayer.getText().toString().isEmpty()){
                    btnAddPlayer.setEnabled(true);
                }else{
                    btnAddPlayer.setEnabled(false);
                }
            }
        });

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("name", edNameOfNewPlayer.getText().toString());
                int wins;
                if(edWinsOfNewPlayer.getText().toString().isEmpty()){
                    wins = 0;
                }else{
                    try {
                        wins = Integer.parseInt(edWinsOfNewPlayer.getText().toString());
                    }catch (NumberFormatException e){
                        Toast.makeText(TeammateAdd.this, "Exception caught, wins = 0", Toast.LENGTH_SHORT).show();
                        wins = 0;
                    }
                }
                intent.putExtra("wins", wins);

                setResult(20, intent);
                finish();
            }
        });

    }
}