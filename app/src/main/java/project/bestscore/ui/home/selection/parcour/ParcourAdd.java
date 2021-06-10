package project.bestscore.ui.home.selection.parcour;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import project.bestscore.R;
import project.bestscore.data.Teammate;

public class ParcourAdd extends AppCompatActivity {
    private EditText edNameOfNewParcour;
    private ImageButton btnAddParcour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_parcour_add);

        edNameOfNewParcour = findViewById(R.id.tvNameOfCountMethod);
        btnAddParcour = findViewById(R.id.btnAddParcour);
        btnAddParcour.setEnabled(false);

        edNameOfNewParcour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!edNameOfNewParcour.getText().toString().isEmpty()){
                    btnAddParcour.setEnabled(true);
                }else{
                    btnAddParcour.setEnabled(false);
                }
            }
        });

        btnAddParcour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("name", edNameOfNewParcour.getText().toString());

                setResult(20, intent);
                finish();
            }
        });

    }
}
