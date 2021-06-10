package project.bestscore.ui.parcours_old;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import project.bestscore.R;

public class ParcourAc_old extends AppCompatActivity {
    private RecyclerView rvParcour;
    private ParcourAdapter_old adapter;
    private ImageButton btnAdd;
    private SearchView svSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_parkour_item, container, false);
        //final TextView textView = root.findViewById(R.id.text_teammates);
        rvParcour = root.findViewById(R.id.rvCountMethods);
        btnAdd = root.findViewById(R.id.btnAdd);
        svSearch = root.findViewById(R.id.svSearch);

        rvParcour.setHasFixedSize(true);
        rvParcour.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParcourAdapter_old(this,this);
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
                Intent intent = new Intent(ParcourAc_old.this, ParcourAdd_old.class);
                startActivityForResult(intent, 100);
            }
        });


        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == 20){
            String name = data.getStringExtra("name");
            Parcour2_old newParcour = new Parcour2_old(name);
            //adapter.newParcour(newParcour);
        }
    }

}
