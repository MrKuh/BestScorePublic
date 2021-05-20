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

public class GameSettingActivity extends AppCompatActivity {

    final static public int REQ_CODE = 100;

    private RecyclerView rvCounts;

    private TextView tvParkour;
    private TextView tvTeammate;
    private TextView tvCountMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting);

        tvParkour = findViewById(R.id.parkour_value);
        tvTeammate = findViewById(R.id.teammates_value);
        tvCountMethod = findViewById(R.id.count_value);


        tvCountMethod.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GameSettingActivity.this, CountMethods.class);
                startActivityForResult(intent, 42);
            }
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
        System.out.println("RESULT");

        if(resultCode == REQ_CODE){
            System.out.println("porn");
        }
    }
}