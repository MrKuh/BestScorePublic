package project.bestscore.ui.home.selection.countmethod;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import project.bestscore.R;
import project.bestscore.data.CountMethod;
import project.bestscore.data.DatabaseHelper;
import project.bestscore.data.Parcour;

public class CountMethodSelectionAdapter extends RecyclerView.Adapter<CountMethodSelectionHolder> {

    private Context context;
    private Activity activity;
    private List<CountMethod> countMethodListAll;
    private List<CountMethod> countMethodList;
    private DatabaseHelper databaseHelper;
    private String filter = "";
    private CountMethodSelection countMethodSelection;

    public CountMethodSelectionAdapter(Context context, Activity activity, CountMethodSelection countMethodSelection) {
        this.context = context;
        this.activity = activity;
        this.countMethodSelection = countMethodSelection;
        databaseHelper = new DatabaseHelper(context);

        CountMethod countMethod = new CountMethod("Variante A");
        if(!databaseHelper.countMethodInserted(countMethod)){
            databaseHelper.insertCountMethod(countMethod);
        }

        updateList();
    }

    @NonNull
    @Override
    public CountMethodSelectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_countmethod_item,parent,false);
        TextView tvNameOfParcour = view.findViewById(R.id.tvNameOfCountMethod);
        ConstraintLayout clBackground = view.findViewById(R.id.clBackground);
        CountMethodSelectionHolder holder = new CountMethodSelectionHolder(view, context, this, tvNameOfParcour, clBackground, countMethodSelection);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountMethodSelectionHolder holder, int position) {
        CountMethod countMethod = countMethodList.get(position);
        holder.getTvNameOfCountMethod().setText(countMethod.getCountMethodName());
    }

    @Override
    public int getItemCount() {
        return countMethodList.size();
    }

    public void newCountMethod(CountMethod newCountMethod) {
        if(!databaseHelper.countMethodInserted(newCountMethod)){
            databaseHelper.insertCountMethod(newCountMethod);
        }
        updateList();
        notifyDataSetChanged();
    }

    public void filter(String filter){
        this.filter = filter;
        if(filter.isEmpty()){
            countMethodList = new ArrayList<>(countMethodListAll);
        }else{
            countMethodList = new ArrayList<>();
            for (CountMethod contact:countMethodListAll) {
                if(contact.getCountMethodName().toLowerCase().contains(filter.toLowerCase())||
                        contact.getCountMethodName().toLowerCase().contains(filter.toLowerCase())){
                    countMethodList.add(contact);
                }
            }
        }
        notifyDataSetChanged();
    }

    public List<CountMethod> getCountMethodList() {
        return countMethodList;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    public CountMethodSelection getCountMethodSelection() {
        return countMethodSelection;
    }

    public void updateList(){
        countMethodListAll = databaseHelper.getCountMethods();
        filter(filter);
    }
}
