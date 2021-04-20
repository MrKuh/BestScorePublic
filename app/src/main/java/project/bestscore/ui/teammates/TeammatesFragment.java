package project.bestscore.ui.teammates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import project.bestscore.R;

public class TeammatesFragment extends Fragment {

    private RecyclerView rvPlayers;
    private ImageButton btnAdd;
    private SearchView svSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_teammates, container, false);
        //final TextView textView = root.findViewById(R.id.text_teammates);
        rvPlayers = root.findViewById(R.id.rvPlayers);
        btnAdd = root.findViewById(R.id.btnAdd);
        svSearch = root.findViewById(R.id.svSearch);

        rvPlayers.setHasFixedSize(true);
        rvPlayers.setLayoutManager(new LinearLayoutManager(this.getContext()));
        //rvPlayers adapter = new ContactAdapter(this);
        //rvPlayers.setAdapter(adapter);


        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.filter(newText);
                return false;
            }
        });


        return root;
    }
}