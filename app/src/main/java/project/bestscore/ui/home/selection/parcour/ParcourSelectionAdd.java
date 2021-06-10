package project.bestscore.ui.home.selection.parcour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import project.bestscore.R;
import project.bestscore.data.DatabaseHelper;
import project.bestscore.data.Parcour;

public class ParcourSelectionAdd extends AppCompatActivity {
    private EditText edNameOfNewParcour;
    private ImageButton btnAddParcour;
    private Context context;

    public ParcourSelectionAdd(){
        context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_parcour_add);

        edNameOfNewParcour = findViewById(R.id.edNameOfNewParcour);
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

                DatabaseHelper db = new DatabaseHelper(context);

                Parcour parcour = new Parcour(edNameOfNewParcour.getText().toString());

                if(!db.parcourInserted(parcour)){
                    Intent intent = getIntent();
                    intent.putExtra(ParcourSelection.getExtraNewParcour(), parcour);
                    setResult(ParcourSelection.getRequestCodeNewParcour(), intent);
                    finish();

                } else {
                    Toast.makeText(context, "Parcour " + parcour.getName() + " is already used", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
