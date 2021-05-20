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
import project.bestscore.data.Teammate;
import project.bestscore.ui.home.selection.player.TeammateSelectionAdapter;
import project.bestscore.ui.parcours.ParcourAdapter;
import project.bestscore.ui.teammates.TeammateAdd;

public class ParcourSelection extends AppCompatActivity {

    private Context context;
    private Button btnContinue;
    private List<Teammate> parcourSelected;

    private RecyclerView rvParcour;
    private ParcourAdapter adapter;
    private ImageButton btnAdd;
    private SearchView svSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcour_selection);

        parcourSelected = new ArrayList<>();

        context = this;
        btnContinue = findViewById(R.id.btnContinue);

        rvParcour = findViewById(R.id.rvPlayers);
        btnAdd = findViewById(R.id.btnAdd);
        svSearch = findViewById(R.id.svSearch);

        rvParcour.setHasFixedSize(true);
        rvParcour.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParcourAdapter(this,this);
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
                Intent intent = new Intent(context, ParcourSelectionAdd.class);
                startActivityForResult(intent, 100);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == 20){
            /*
            String name = data.getStringExtra("name");
            int wins = data.getIntExtra("wins", 0);
            Teammate newTeammate = new Teammate(name, wins);
            adapter.newTeammate(newTeammate);
             */
        }
    }

    public void addSelectedTeammate(Teammate teammate){
        parcourSelected.add(teammate);
    }

    public void deleteSelectedTeammate(Teammate teammate){
        parcourSelected.remove(teammate);
    }
}