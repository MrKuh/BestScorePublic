package project.bestscore.ui.home.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import project.bestscore.R;

public class GameEventActivity extends AppCompatActivity {

    private int amount = 1;
    private ArrayList<int[]> arrows;
    private int arrowAmount = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_event);




    }
}