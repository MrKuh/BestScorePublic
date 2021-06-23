package project.bestscore.ui.home.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.bestscore.R;
import project.bestscore.data.Teammate;
import project.bestscore.ui.home.selection.countmethod.CountMethodSelectionHolder;

public class GamePlayerAdapter extends RecyclerView.Adapter<GamePlayerHolder>{

    private TextView tvPlayerName;
    private TextView arrow31;
    private TextView arrow32;
    private TextView arrow33;
    private Context context;
    private ArrayList<Teammate> mates;
    private ArrayList<int[]> arrows;

    private GamePlayerHolder holder;

    public GamePlayerAdapter(Context context, ArrayList<int[]> arrows, ArrayList<Teammate> mates) {
        this.context = context;
        this.arrows = arrows;
    }

    @NonNull
    @Override
    public GamePlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_game_player_item_3, parent, false);

        holder = new GamePlayerHolder(view, context, tvPlayerName, arrow31, arrow32, arrow33);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GamePlayerHolder holder, int position) {

        

        holder.getTvPlayerName();
        holder.getArrow31();
        holder.getArrow32();
        holder.getArrow33();
    }

    @Override
    public int getItemCount() {
        return mates.size();
    }
}
