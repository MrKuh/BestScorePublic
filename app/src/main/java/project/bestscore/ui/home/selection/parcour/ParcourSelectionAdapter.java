package project.bestscore.ui.home.selection.parcour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class ParcourSelectionAdapter extends RecyclerView.Adapter<ParcourSelectionHolder> {
    private Activity activity;
    private List<Parcour> parcourListAll;
    private List<Parcour> parcourList;
    private DatabaseHelper databaseHelper;
    private String filter = "";

    public ParcourSelectionAdapter(Activity activity) {
        this.activity = activity;
        databaseHelper = new DatabaseHelper(activity);
        Parcour parcour = new Parcour("Hanslstadt");
        if(!databaseHelper.parcourInserted(parcour)){
            databaseHelper.insertParcour(parcour);
        }

        updateList();
    }

    @NonNull
    @Override
    public ParcourSelectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_parkour,parent,false);
        TextView tvNameOfParcour = view.findViewById(R.id.tvNameOfParcour);
        ParcourSelectionHolder parkourHolder = new ParcourSelectionHolder(view, activity, this,tvNameOfParcour);
        return parkourHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParcourSelectionHolder holder, int position) {
        Parcour parcour = parcourList.get(position);
        holder.getTvNameOfParcour().setText(parcour.getName());
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
                if(parcour.getName().toLowerCase().contains(filter.toLowerCase())||
                        parcour.getName().toLowerCase().contains(filter.toLowerCase())){
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
        parcourListAll = databaseHelper.getParcours();
        filter(filter);
    }

    public Parcour getParcour(int index) {
        return parcourList.get(index);
    }
}
