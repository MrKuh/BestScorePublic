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
import project.bestscore.ui.home.game_settings.GameSettingActivity;

public class ParcourSelection extends AppCompatActivity {

    private RecyclerView rvParcour;
    private ParcourSelectionAdapter adapter;
    private ImageButton btnAdd;
    private SearchView svSearch;
    private Context context;
    private Button btnContinue;
    private List<Parcour> parcourSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcour_selection);

        parcourSelected = new ArrayList<>();

        rvParcour = findViewById(R.id.rvCountMethods);
        btnAdd = findViewById(R.id.btnAdd);
        svSearch = findViewById(R.id.svSearch);
        context = this;
        btnContinue = findViewById(R.id.btnContinue);

        rvParcour.setHasFixedSize(true);
        rvParcour.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParcourSelectionAdapter(this,this,this);
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
                startActivityForResult(intent, 101);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                if(!parcourSelected.isEmpty()){
                    intent.putExtra("name", parcourSelected.get(0).getParcourName());
                }
                setResult(GameSettingActivity.REQ_CODE_PARCOUR, intent);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101 && resultCode == 21){
            String name = data.getStringExtra("name");
            Parcour newParcour = new Parcour(name);
            adapter.newParcour(newParcour);
        }
    }

    public List<Parcour> getParcourSelected() {
        return parcourSelected;
    }

    public void addSelectedParcour(Parcour parcour){
        parcourSelected.add(parcour);
    }

    public void deleteSelectedParcour(Parcour parcour){
        parcourSelected.remove(parcour);
    }
}