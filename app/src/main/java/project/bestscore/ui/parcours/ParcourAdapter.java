package project.bestscore.ui.parcours;

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

public class ParcourAdapter extends RecyclerView.Adapter<ParcourHolder> {
    private Context context;
    private Activity activity;
    private List<Parcour> parcourListAll;
    private List<Parcour> parcourList;
    private DatabaseHelper databaseHelper;
    private String filter = "";

    public ParcourAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        databaseHelper = new DatabaseHelper(context);
        Parcour parcour = new Parcour("Hanslstadt");
        if(!databaseHelper.parcourInserted(parcour)){
            databaseHelper.insertParcour(parcour);
        }
        updateList();
    }

    @NonNull
    @Override
    public ParcourHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_parkour,parent,false);
        TextView tvNameOfParcour = view.findViewById(R.id.tvNameOfParcour);
        ParcourHolder parkourHolder = new ParcourHolder(view, context, this,tvNameOfParcour);
        return parkourHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParcourHolder holder, int position) {
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
        parcourListAll = databaseHelper.getParcour();
        filter(filter);
    }
}
