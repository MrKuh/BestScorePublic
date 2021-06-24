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
import androidx.recyclerview.widget.ItemTouchHelper;
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
    private CountMethod countMethodSelected;
    private CountMethodSelectionHolder countMethodSelectionHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countmethod_selection);

        countMethodSelected = null;
        countMethodSelectionHolder = null;

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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(rvCountMethod);


        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                if(countMethodSelected != null){
                    intent.putExtra("name", countMethodSelected.getCountMethodName());
                }
                setResult(GameSettingActivity.REQ_CODE_COUNTMETHOD, intent);
                finish();
            }
        });

        btnContinue.setEnabled(false);
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

    private ItemTouchHelper.Callback createHelperCallback(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove( RecyclerView recyclerView,  RecyclerView.ViewHolder viewHolder,  RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped( RecyclerView.ViewHolder viewHolder, int direction) {
                        deleteItem(viewHolder.getAdapterPosition());
                    }
                };
        return simpleItemTouchCallback;
    }

    private void deleteItem(final int position) {

        //adapter.notifyItemRemoved(position);
        adapter.deleteItem(position);
    }

    public CountMethod getCountMethodSelected() {
        return countMethodSelected;
    }

    public void addSelectedCountMethod(CountMethod countMethod) {
        countMethodSelected = countMethod;
    }

    public void deleteSelectedCountMethod(){
        countMethodSelected = null;
    }

    public void setSelectionHolder(CountMethodSelectionHolder holder){
        countMethodSelectionHolder = holder;
    }

    public void changeColor(){
        countMethodSelectionHolder.getClBackground().setBackgroundResource(R.drawable.list_background_rounded);
        countMethodSelectionHolder.setSelected(false);
    }

    public void setBtnContinue(boolean enable) {
        btnContinue.setEnabled(enable);
    }
}