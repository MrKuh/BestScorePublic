package project.bestscore.ui.teammates;

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
import project.bestscore.data.Teammate;

public class TeammateAdapter extends RecyclerView.Adapter<TeammateViewholder> {

    private Context context;
    private Activity activity;
    private List<Teammate> teammateList;

    public TeammateAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        //teammateList.add(new Teammate())
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        Teammate mate = new Teammate("Martin", 50);
        if(!databaseHelper.teammateInserted(mate)){
            databaseHelper.insertTeammate(mate);
        }
        mate = new Teammate("Michael M.", 51);
        if(!databaseHelper.teammateInserted(mate)){
            databaseHelper.insertTeammate(mate);
        }
        mate = new Teammate("Michael W.", 49);
        if(!databaseHelper.teammateInserted(mate)){
            databaseHelper.insertTeammate(mate);
        }
        mate = new Teammate("Benedikt", 10);
        if(!databaseHelper.teammateInserted(mate)){
            databaseHelper.insertTeammate(mate);
        }

        teammateList = databaseHelper.getTeammates();
    }

    @NonNull
    @Override
    public TeammateViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_teammates_item,parent,false);
        TextView tvNameOfPlayer = view.findViewById(R.id.tvNameOfPlayer);
        TextView tvWinsOfPlayer = view.findViewById(R.id.tvWinsOfPlayer);
        TeammateViewholder holder = new TeammateViewholder(view, activity, context, tvNameOfPlayer, tvWinsOfPlayer);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeammateViewholder holder, int position) {
        Teammate teammate = teammateList.get(position);

        holder.getTvNameOfPlayer().setText(teammate.getName());
        holder.getTvWinsOfPlayer().setText("Wins: " + teammate.getWins());

    }

    @Override
    public int getItemCount() {
        return teammateList.size();
    }
}
