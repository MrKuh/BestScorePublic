package project.bestscore.ui.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import project.bestscore.R;
import project.bestscore.data.Event;
import project.bestscore.ui.events.EventAdapter;

public class GameSettingActivity extends AppCompatActivity {

    private RecyclerView rvCounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting);

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
}