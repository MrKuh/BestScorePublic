package project.bestscore.ui.parcours_old;

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

public class ParcourAdapter_old extends RecyclerView.Adapter<ParcourHolder_old> {
    private Context context;
    private Activity activity;
    private List<Parcour2_old> parcourListAll;
    private List<Parcour2_old> parcourList;
    private DatabaseHelper databaseHelper;
    private String filter = "";

    public ParcourAdapter_old(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        /*
        databaseHelper = new DatabaseHelper(context);
        Parcour2 parcour = new Parcour2("Hanslstadt");
        if(!databaseHelper.parcourInserted(parcour)){
            databaseHelper.insertParcour(parcour);
        }
        updateList();

         */
    }

    @NonNull
    @Override
    public ParcourHolder_old onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_parcour_item,parent,false);
        TextView tvNameOfParcour = view.findViewById(R.id.tvNameOfParcour);
        ParcourHolder_old parkourHolder = new ParcourHolder_old(view, context, this,tvNameOfParcour);
        return parkourHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParcourHolder_old holder, int position) {
        Parcour2_old parcour = parcourList.get(position);
        holder.getTvNameOfParcour().setText(parcour.getParcourName());
    }

    @Override
    public int getItemCount() {
        return parcourList.size();
    }
    /*
    public void newParcour(Parcour2 newParcour) {
        if(!databaseHelper.parcourInserted(newParcour)){
            databaseHelper.insertParcour(newParcour);
        }
        updateList();
        notifyDataSetChanged();
    }

     */

    public void filter(String filter){
        this.filter = filter;
        if(filter.isEmpty()){
            parcourList = new ArrayList<>(parcourListAll);
        }else{
            parcourList = new ArrayList<>();
            for (Parcour2_old parcour:parcourListAll) {
                if(parcour.getParcourName().toLowerCase().contains(filter.toLowerCase())||
                        parcour.getParcourName().toLowerCase().contains(filter.toLowerCase())){
                    parcourList.add(parcour);
                }
            }
        }
        notifyDataSetChanged();
    }

    public List<Parcour2_old> getParcourList() {
        return parcourList;
    }
    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    /*
    public void updateList(){
        parcourListAll = databaseHelper.getParcour();
        filter(filter);
    }

     */
}
