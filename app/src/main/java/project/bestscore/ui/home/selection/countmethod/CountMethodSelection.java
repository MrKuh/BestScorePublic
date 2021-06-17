package project.bestscore.ui.home.selection.countmethod;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import project.bestscore.R;
import project.bestscore.data.CountMethod;
import project.bestscore.ui.home.game.GameSettingActivity;

public class CountMethodSelection extends AppCompatActivity {

    private RecyclerView rvCountMethod;
    private CountMethodSelectionAdapter adapter;
    private ImageButton btnAdd;
    private SearchView svSearch;
    private Context context;
    private Button btnContinue;
    private List<CountMethod> countMethodSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countmethod_selection);

        countMethodSelected = new ArrayList<>();

        rvCountMethod = findViewById(R.id.rvCountMethods);
        btnAdd = findViewById(R.id.btnAdd);
        svSearch = findViewById(R.id.svSearch);
        context = this;
        btnContinue = findViewById(R.id.btnContinue);



        rvCountMethod.setHasFixedSize(true);
        rvCountMethod.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CountMethodSelectionAdapter(this,this,this);
        rvCountMethod.setAdapter(adapter);


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
                Intent intent = new Intent(context, CountMethodAdd.class);
                startActivityForResult(intent, 103);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                if(!countMethodSelected.isEmpty()){
                    intent.putExtra("name", countMethodSelected.get(0).getCountMethodName());
                }
                setResult(GameSettingActivity.REQ_CODE_COUNTMETHOD, intent);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 103 && resultCode == 23){
            String name = data.getStringExtra("name");
            CountMethod newCountMethod = new CountMethod(name);
            adapter.newCountMethod(newCountMethod);
        }
    }

    public CountMethod getCountMethodSelected() {
        return countMethodSelected.get(0);
    }
    public List<CountMethod>  getCountMethodsSelected() {
        return countMethodSelected;
    }

    public void addSelectedCountMethod(CountMethod countMethod){
        countMethodSelected.add(countMethod);
    }

    public void deleteSelectedCountMethod(CountMethod countMethod){
        countMethodSelected.remove(countMethod);
    }
}