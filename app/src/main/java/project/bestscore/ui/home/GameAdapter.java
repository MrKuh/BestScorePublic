package project.bestscore.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import project.bestscore.R;

public class GameAdapter extends RecyclerView.Adapter<GameHolder> {

    private Context context;

    private List<GameModel> games = Arrays.asList(
            new GameModel("Bogenschießen", R.drawable.ic_archery)
    );

    public GameAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public GameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_item,parent,false);
        TextView tvName = view.findViewById(R.id.modelDetailView);
        ImageView ivPicture = view.findViewById(R.id.modelImageView);
        GameHolder gameHolder = new GameHolder(view, tvName, ivPicture, context);
        return gameHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GameHolder holder, int position) {
        GameModel gameModel = games.get(position);
        holder.getTvName().setText(gameModel.getName());
        holder.getImgBow().setImageResource(gameModel.getPicture());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }
}

