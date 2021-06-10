package project.bestscore.ui.home.game_settings;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import project.bestscore.R;
import project.bestscore.data.Parcour;
import project.bestscore.data.Teammate;
import project.bestscore.ui.home.selection.parcour.ParcourSelection;
import project.bestscore.ui.home.selection.player.TeammateSelection;
import project.bestscore.ui.home.selection.player.TeammateSelectionAdapter;

public class GameSettingActivity extends AppCompatActivity {

    private static final int requestCodeParcour = 1;
    private static final int requestCodeTeammates = 2;

    private static final String extraParcourName = "selectredParcour";
    private static final String extraTeammatesName = "selectedTeammates";

    private RecyclerView rvCounts;
    private TextView tvParcour;
    private TextView tvTeammate;
    private TextView tvCountMethod;

    private Parcour selectedParcour;
    private ArrayList<Teammate> selectedTeammates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting);

        tvParcour = findViewById(R.id.parkour_value);
        tvTeammate = findViewById(R.id.teammates_value);
        tvCountMethod = findViewById(R.id.count_value);


        tvCountMethod.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GameSettingActivity.this, CountMethods.class);
                startActivityForResult(intent, 42);
            }
        });

        tvParcour.setOnClickListener(v -> {
            Intent intent = new Intent(this, ParcourSelection.class);
            startActivityForResult(intent, requestCodeParcour);
        });

        tvTeammate.setOnClickListener(v -> {
            Intent intent = new Intent(this, TeammateSelection.class);
            startActivityForResult(intent, requestCodeTeammates);
        });

        ArrayList<String[]> zones = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            String[] zone = new String[5];
            zone[0] = (getString(R.string.settings_arrow) + ": " + i);
            System.out.println(zone[0]);
            zones.add(zone);
        }

        rvCounts = this.findViewById(R.id.rvCount);
        rvCounts.setHasFixedSize(true);
        rvCounts.setLayoutManager(new LinearLayoutManager(this));
        rvCounts.setAdapter(new CountAdapter(zones));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == requestCodeParcour){
            selectedParcour = (Parcour) data.getSerializableExtra(extraParcourName);
            tvParcour.setText(selectedParcour.getName());
        } else if(resultCode == requestCodeTeammates){
            selectedTeammates = (ArrayList<Teammate>) data.getSerializableExtra(extraTeammatesName);
            tvTeammate.setText(String.valueOf(selectedTeammates.size()));
        }
    }

    public static int getRequestCodeParcour(){
        return requestCodeParcour;
    }

    public static int getRequestCodeTeammates(){
        return requestCodeTeammates;
    }

    public static String getExtraParcourName(){
        return extraParcourName;
    }

    public static String getExtraTeammatesName(){
        return extraTeammatesName;
    }
}