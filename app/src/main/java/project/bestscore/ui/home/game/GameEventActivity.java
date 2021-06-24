package project.bestscore.ui.home.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import project.bestscore.R;
import project.bestscore.data.Teammate;
import project.bestscore.ui.home.GameAdapter;
import project.bestscore.ui.home.selection.countmethod.CountMethodSelectionAdapter;

public class GameEventActivity extends AppCompatActivity {

    //private int amount = 1;
    private int arrowAmount = 1;
    private ArrayList<int[]> arrows;
    private ArrayList<String> mates;

    private RecyclerView rvGamePlayer;
    private GamePlayerAdapter gamePlayerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_event);

        rvGamePlayer = findViewById(R.id.rvGamePlayer);
        arrows = new ArrayList<>();


        int[] values = {28, 26, 24, 22};
        arrows.add(values);
        values = new int[]{20, 18, 16, 14};
        arrows.add(values);
        values = new int[]{12, 10, 8, 6};
        arrows.add(values);


        mates = getIntent().getStringArrayListExtra("mates");

        rvGamePlayer.setHasFixedSize(true);
        rvGamePlayer.setLayoutManager(new LinearLayoutManager(this));
        gamePlayerAdapter = new GamePlayerAdapter(this, arrows, mates);
        rvGamePlayer.setAdapter(gamePlayerAdapter);
    }


}