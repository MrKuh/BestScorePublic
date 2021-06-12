package project.bestscore.ui.home.selection.parcour;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import project.bestscore.R;
import project.bestscore.data.Parcour;
import project.bestscore.data.Teammate;
import project.bestscore.ui.home.game_settings.GameSettingActivity;

public class ParcourSelection extends AppCompatActivity {


    private List<Parcour> parcoursSelected;

    private RecyclerView rvParcour;
    private ParcourSelectionAdapter adapter;
    private Context context;

    private Parcour parcour = new Parcour(1, "TestP");

    private ArrayList<Teammate> teammatesSelected = new ArrayList<Teammate>();

    private ImageButton btnAdd;
    private SearchView svSearch;


    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcour_selection);

        context = this;
        btnContinue = findViewById(R.id.btnContinue);

        rvParcour = findViewById(R.id.rvCountMethods);
        btnAdd = findViewById(R.id.btnAdd);
        svSearch = findViewById(R.id.svSearch);

        rvParcour.setHasFixedSize(true);
        rvParcour.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParcourSelectionAdapter(this,this);
        rvParcour.setAdapter(adapter);


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
                Intent intent = new Intent(context, ParcourAdd.class);
                startActivityForResult(intent, 200);

            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parcour parcour = new Parcour(1, "TestP");
                System.out.println("TEST");
                Intent intent = getIntent();
                intent.putExtra("amount", parcour.getParcourName());
                setResult(GameSettingActivity.REQ_CODE_PARCOUR, intent);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 200 && resultCode == 20){
            String name = data.getStringExtra("name");
        }
    }

    public void addSelectedParcour(Teammate teammate){
        teammatesSelected.add(teammate);
    }

    public void deleteSelectedParcourbn (Teammate teammate){
        teammatesSelected.remove(teammate);
    }
}