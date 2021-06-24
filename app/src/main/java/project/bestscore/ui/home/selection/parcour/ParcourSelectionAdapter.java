package project.bestscore.ui.home.selection.parcour;

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
import project.bestscore.data.DatabaseHelper;
import project.bestscore.data.Parcour;

public class ParcourSelectionAdapter extends RecyclerView.Adapter<ParcourSelectionHolder> {

    private Context context;
    private Activity activity;
    private List<Parcour> parcourListAll;
    private List<Parcour> parcourList;
    private DatabaseHelper databaseHelper;
    private String filter = "";
    private ParcourSelection parcourSelection;

    public ParcourSelectionAdapter(Context context, Activity activity, ParcourSelection parcourSelection) {
        this.context = context;
        this.activity = activity;
        this.parcourSelection = parcourSelection;
        databaseHelper = new DatabaseHelper(context);

        Parcour parcour = new Parcour("Griasboch");
        if(!databaseHelper.parcourInserted(parcour)){
            databaseHelper.insertParcour(parcour);
        }

        updateList();
    }


    @NonNull
    @Override
    public ParcourSelectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_parcour_item,parent,false);
        TextView tvNameOfParcour = view.findViewById(R.id.tvNameOfParcour);
        ConstraintLayout clBackground = view.findViewById(R.id.clBackground);
        ParcourSelectionHolder holder = new ParcourSelectionHolder(view, context, this, tvNameOfParcour, clBackground, parcourSelection);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParcourSelectionHolder holder, int position) {
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
            for (Parcour contact:parcourListAll) {
                if(contact.getParcourName().toLowerCase().contains(filter.toLowerCase())||
                        contact.getParcourName().toLowerCase().contains(filter.toLowerCase())){
                    parcourList.add(contact);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void deleteItem(int position){
        Parcour parcour = getParcourList().get(position);
        getDatabaseHelper().deleteParcour(parcour);
        updateList();
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
}
