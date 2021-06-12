package project.bestscore.ui.teammates;

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
import project.bestscore.data.Teammate;

public class TeammatesFragment extends Fragment {

    private RecyclerView rvPlayers;
    private TeammateAdapter adapter;
    private ImageButton btnAdd;
    private SearchView svSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_teammates, container, false);
        //final TextView textView = root.findViewById(R.id.text_teammates);
        rvPlayers = root.findViewById(R.id.rvParcours);
        btnAdd = root.findViewById(R.id.btnAdd);
        svSearch = root.findViewById(R.id.svSearch);

        rvPlayers.setHasFixedSize(true);
        rvPlayers.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new TeammateAdapter(this.getContext(),this.getActivity());
        rvPlayers.setAdapter(adapter);


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
                Intent intent = new Intent(getContext(), TeammateAdd.class);
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
            int wins = data.getIntExtra("wins", 0);
            Teammate newTeammate = new Teammate(name, wins);
            adapter.newTeammate(newTeammate);
        }
    }
}