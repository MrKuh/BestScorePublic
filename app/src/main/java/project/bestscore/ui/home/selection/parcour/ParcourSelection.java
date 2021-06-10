package project.bestscore.ui.home.selection.parcour;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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
import project.bestscore.ui.home.selection.player.TeammateSelectionAdapter;
import project.bestscore.ui.teammates.TeammateAdd;

public class ParcourSelection extends AppCompatActivity {

    private Context context;
    private Button btnContinue;
    private Parcour parcourSelected;

    private RecyclerView rvParcour;
    private ParcourSelectionAdapter adapter;
    private ImageButton btnAdd;
    private SearchView svSearch;

    private static final int requestCodeNewParcour = 3;
    private static final String extraNewParcour = "newParcour";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcour_selection);

        context = this;
        btnContinue = findViewById(R.id.btnContinue);

        rvParcour = findViewById(R.id.rvPlayers);
        btnAdd = findViewById(R.id.btnAdd);
        svSearch = findViewById(R.id.svSearch);

        rvParcour.setHasFixedSize(true);
        rvParcour.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParcourSelectionAdapter(this);
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
                startActivityForResult(intent, requestCodeNewParcour);
            }
        });

    }

    public void setSelectedParcour(Parcour parcour){
        parcourSelected = parcour;
    }

    public static int getRequestCodeNewParcour(){
        return requestCodeNewParcour;
    }

    public static String getExtraNewParcour(){
        return extraNewParcour;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == requestCodeNewParcour){
            adapter.newParcour((Parcour) data.getSerializableExtra(extraNewParcour));
        }
    }

}