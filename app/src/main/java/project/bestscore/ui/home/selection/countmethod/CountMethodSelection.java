package project.bestscore.ui.home.selection.countmethod;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import project.bestscore.R;
import project.bestscore.ui.home.game_settings.CountAdapter;
import project.bestscore.ui.home.game_settings.GameSettingActivity;
import project.bestscore.ui.home.selection.parcour.Parcour;
import project.bestscore.ui.home.selection.parcour.ParcourAdd;

public class CountMethodSelection extends AppCompatActivity {

    private Context context;


    private RecyclerView rvCountmethods;
    private CountMethodSelectionAdapter adapter;
    private ImageButton btnAdd;
    private SearchView svSearch;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countmethod_selection);

        context = this;
        btnContinue = findViewById(R.id.btnContinue);

        rvCountmethods = findViewById(R.id.rvCountmethods);
        btnAdd = findViewById(R.id.btnAdd);
        svSearch = findViewById(R.id.svSearch);

        ArrayList<String[]> zones = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            String[] zone = new String[5];
            zone[0] = (getString(R.string.settings_arrow) + ": " + i);
            System.out.println(zone[0]);
            zones.add(zone);
        }

        rvCountmethods = this.findViewById(R.id.rvCountmethods);
        rvCountmethods.setNestedScrollingEnabled(false);
        rvCountmethods.setHasFixedSize(true);
        rvCountmethods.setLayoutManager(new LinearLayoutManager(this));
        rvCountmethods.setAdapter(new CountAdapter(zones));




        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountMethodAdd.class);
                startActivityForResult(intent, 300);

            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "test";
                Intent intent = new Intent();
                intent.putExtra("SELECTED_METHOD", data);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 300 && resultCode == 20){
            String name = data.getStringExtra("name");
        }
    }
}