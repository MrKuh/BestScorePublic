package project.bestscore.ui.home.selection.player;

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
import project.bestscore.ui.home.game_settings.GameSettingActivity;
import project.bestscore.ui.teammates.TeammateAdd;

public class TeammateSelection extends AppCompatActivity {

    private RecyclerView rvPlayers;
    private TeammateSelectionAdapter adapter;
    private ImageButton btnAdd;
    private SearchView svSearch;
    private Context context;
    private Button btnContinue;
    private List<Teammate> teammatesSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teammate_selection);

        teammatesSelected = new ArrayList<>();

        rvPlayers = findViewById(R.id.rvParcours);
        btnAdd = findViewById(R.id.btnAdd);
        svSearch = findViewById(R.id.svSearch);
        context = this;
        btnContinue = findViewById(R.id.btnContinue);

        rvPlayers.setHasFixedSize(true);
        rvPlayers.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TeammateSelectionAdapter(this,this,this);
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
                Intent intent = new Intent(context, TeammateAdd.class);
                startActivityForResult(intent, 100);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("amount", teammatesSelected.size());

                setResult(GameSettingActivity.REQ_CODE_TEAMMATE, intent);

                /*
                Bundle args = new Bundle();
                args.putSerializable("selectedMates", (Serializable) teammatesSelected);
                intent.putExtra("bundle",args);
                setResult(GameSettingActivity.REQ_CODE_TEAMMATE, intent);
                 */
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == 20){
            String name = data.getStringExtra("name");
            int wins = data.getIntExtra("wins", 0);
            Teammate newTeammate = new Teammate(name, wins);
            adapter.newTeammate(newTeammate);
        }
    }

    public void addSelectedTeammate(Teammate teammate){
        teammatesSelected.add(teammate);
    }

    public void deleteSelectedTeammate(Teammate teammate){
        teammatesSelected.remove(teammate);
    }
}