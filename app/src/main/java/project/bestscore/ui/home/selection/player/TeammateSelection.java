package project.bestscore.ui.home.selection.player;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import project.bestscore.ui.home.game.GameSettingActivity;
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

        rvPlayers = findViewById(R.id.rvCountMethods);
        btnAdd = findViewById(R.id.btnAdd);
        svSearch = findViewById(R.id.svSearch);
        context = this;
        btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setEnabled(false);

        rvPlayers.setHasFixedSize(true);
        rvPlayers.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TeammateSelectionAdapter(this, this, this);
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

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TeammateAdd.class);
                startActivityForResult(intent, 100);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(rvPlayers);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                ArrayList<String> names = new ArrayList<String>();
                for (Teammate teammate : teammatesSelected) {
                    names.add(teammate.getName());
                }

                intent.putExtra("names", names);
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

        if (requestCode == 100 && resultCode == 20) {
            String name = data.getStringExtra("name");
            int wins = data.getIntExtra("wins", 0);
            Teammate newTeammate = new Teammate(name, wins);
            adapter.newTeammate(newTeammate);
        }
    }
    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        adapter.deleteItem(viewHolder.getAdapterPosition());
                    }
                };
        return simpleItemTouchCallback;
    }

    public void addSelectedTeammate(Teammate teammate) {
        teammatesSelected.add(teammate);
        btnContinue.setEnabled(true);

    }

    public void deleteSelectedTeammate(Teammate teammate) {
        teammatesSelected.remove(teammate);
        if(teammatesSelected.size() == 0){
            btnContinue.setEnabled(false);
        }

    }
}