package project.bestscore.ui.home.selection.countmethod;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.bestscore.R;

import static project.bestscore.ui.home.game.GameSettingActivity.AMOUNT_ARROWS;

public class CountMethodAdd extends AppCompatActivity {
    private EditText edNameOfNewCountMethod;
    private ArrayList<String[]> zones;
    private ImageButton btnAddCountMethod;
    private RecyclerView rvCounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_countmethod_add);

        edNameOfNewCountMethod = findViewById(R.id.edNameOfCountMethod);

        zones = new ArrayList<>();
        for (int i = 1; i < AMOUNT_ARROWS + 1; i++) {
            String[] zone = new String[5];
            zone[0] = (getString(R.string.settings_arrow) + ": " + i);
            System.out.println(zone[0]);
            zones.add(zone);
        }
        rvCounts = this.findViewById(R.id.rvCountMethodsAdd);
        rvCounts.setNestedScrollingEnabled(false);
        rvCounts.setHasFixedSize(true);
        rvCounts.setLayoutManager(new LinearLayoutManager(this));
        rvCounts.setAdapter(new CountAdapter(zones));


        btnAddCountMethod = findViewById(R.id.btnAddCountMethod);
        btnAddCountMethod.setEnabled(false);

        edNameOfNewCountMethod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!edNameOfNewCountMethod.getText().toString().isEmpty()){
                    btnAddCountMethod.setEnabled(true);
                }else{
                    btnAddCountMethod.setEnabled(false);
                }
            }
        });

        btnAddCountMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("name", edNameOfNewCountMethod.getText().toString());
                System.out.println(edNameOfNewCountMethod.getText().toString());

                setResult(23, intent);
                finish();
            }
        });

    }
}
