package project.bestscore.ui.home.game_settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import project.bestscore.R;

public class CountMethods extends AppCompatActivity {

    private TextView tvCustom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_methods);

        tvCustom = findViewById(R.id.tvCustom);

        tvCustom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String data = tvCustom.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("SELECTED_METHOD", data);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        /*
        tvCustom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                setResult(RESULT_CANCELED);
                finish();
            }
        });
         */


    }
}