package project.bestscore.ui.parcours;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import project.bestscore.R;

public class ParcourFragment extends Fragment {
    private RecyclerView rvParcour;
    private ParcourAdapter adapter;
    private ImageButton btnAdd;
    private SearchView svSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_parkour, container, false);
        //final TextView textView = root.findViewById(R.id.text_teammates);
        rvParcour = root.findViewById(R.id.rvPlayers);
        btnAdd = root.findViewById(R.id.btnAdd);
        svSearch = root.findViewById(R.id.svSearch);

        rvParcour.setHasFixedSize(true);
        rvParcour.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new ParcourAdapter(this.getContext(),this.getActivity());
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
                Intent intent = new Intent(getContext(), ParcourAdd.class);
                startActivityForResult(intent, 100);
            }
        });


        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100&&resultCode==20){
            String name = data.getStringExtra("name");
            Parcour newParcour = new Parcour(name);
            adapter.newParcour(newParcour);
        }
    }

}
