package project.bestscore.ui.home.selection.countmethod.old;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import project.bestscore.R;
import project.bestscore.data.DatabaseHelper;
import project.bestscore.data.Parcour;

public class CountMethodSelectionAdapter_old extends RecyclerView.Adapter<CountMethodSelectionHolder_old> {
    private Context context;
    private Activity activity;
    private List<Parcour> parcourListAll;
    private List<Parcour> parcourList;
    private DatabaseHelper databaseHelper;
    private String filter = "";

    public CountMethodSelectionAdapter_old(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        /*
        databaseHelper = new DatabaseHelper(context);
        Parcour parcour = new Parcour("Hanslstadt");
        if(!databaseHelper.parcourInserted(parcour)){
            databaseHelper.insertParcour(parcour);
        }

         */

        updateList();
    }

    @NonNull
    @Override
    public CountMethodSelectionHolder_old onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_parcour_item,parent,false);
        TextView tvNameOfCountMethod = view.findViewById(R.id.tvNameOfCountMethod);
        CountMethodSelectionHolder_old parkourHolder = new CountMethodSelectionHolder_old(view, context, this,tvNameOfCountMethod);
        return parkourHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountMethodSelectionHolder_old holder, int position) {
        Parcour parcour = parcourList.get(position);
        holder.getTvNameOfParcour().setText(parcour.getParcourName());
    }

    @Override
    public int getItemCount() {
        return parcourList.size();
    }

    public void newParcour(Parcour newParcour) {
        if(!databaseHelper.parcourInserted(newParcour)){
            databaseHelper.insertParcour(newParcour);
        }
        updateList();
        notifyDataSetChanged();
    }

    public void filter(String filter){
        this.filter = filter;
        if(filter.isEmpty()){
            parcourList = new ArrayList<>(parcourListAll);
        }else{
            parcourList = new ArrayList<>();
            for (Parcour parcour:parcourListAll) {
                if(parcour.getParcourName().toLowerCase().contains(filter.toLowerCase())||
                        parcour.getParcourName().toLowerCase().contains(filter.toLowerCase())){
                    parcourList.add(parcour);
                }
            }
        }
        notifyDataSetChanged();
    }

    public List<Parcour> getParcourList() {
        return parcourList;
    }
    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    public void updateList(){
        //parcourListAll = databaseHelper.getParcours();
        ArrayList<Parcour> pacours = new ArrayList<Parcour>();
        pacours.add(new Parcour("TEST"));
        parcourListAll = pacours;
        filter(filter);
    }
}
