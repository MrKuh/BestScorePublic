package project.bestscore.ui.home.game;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import project.bestscore.R;
import project.bestscore.ui.home.selection.countmethod.CountMethodSelection;
import project.bestscore.ui.home.selection.parcour.ParcourSelection;
import project.bestscore.ui.home.selection.player.TeammateSelection;

public class GameSettingActivity extends AppCompatActivity {

    final static public int REQ_CODE_PARCOUR = 11;
    final static public int REQ_CODE_TEAMMATE = 12;
    final static public int REQ_CODE_COUNTMETHOD = 13;

    final static public int AMOUNT_ARROWS = 4;

    private TextView tvParcour;
    private TextView tvTeammate;
    private TextView tvCountMethod;

    private Boolean countMethodSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting);

        tvParcour = findViewById(R.id.arrow21);
        tvTeammate = findViewById(R.id.teammates_value);
        tvCountMethod = findViewById(R.id.count_value);


        tvParcour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GameSettingActivity.this, ParcourSelection.class);
                startActivityForResult(intent, REQ_CODE_PARCOUR);
            }
        });

        tvTeammate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GameSettingActivity.this, TeammateSelection.class);
                startActivityForResult(intent, REQ_CODE_TEAMMATE);
            }
        });
        
        tvCountMethod.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GameSettingActivity.this, CountMethodSelection.class);
                startActivityForResult(intent, REQ_CODE_COUNTMETHOD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("RESULT");

        if(resultCode == REQ_CODE_PARCOUR) {
            String name = data.getStringExtra("name");
            if(name == null){
                name = getString(R.string.settings_select);
            }
            tvParcour.setText(name);

        }

        if(resultCode == REQ_CODE_TEAMMATE){
            int amount = data.getIntExtra("amount", -1);
            ArrayList<String> names = data.getStringArrayListExtra("names");
            tvTeammate.setText(amount + "");

        }

        if(resultCode == REQ_CODE_COUNTMETHOD){
            String name = data.getStringExtra("name");
            if(name == null){
                name = getString(R.string.settings_select);
                countMethodSelected = false;
            }else{
                countMethodSelected = true;

            }
            tvCountMethod.setText(name);

        }



    }

}