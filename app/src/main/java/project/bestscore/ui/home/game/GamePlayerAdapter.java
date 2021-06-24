package project.bestscore.ui.home.game;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import project.bestscore.R;
import project.bestscore.data.DatabaseHelper;
import project.bestscore.data.Teammate;
import project.bestscore.ui.home.selection.countmethod.CountMethodSelectionHolder;

public class GamePlayerAdapter extends RecyclerView.Adapter<GamePlayerHolder>{

    private Context context;
    private ArrayList<Teammate> mates;
    private ArrayList<int[]> arrows;

    private GamePlayerHolder holder;
    private DatabaseHelper databaseHelper;

    private View view;

    private int points = 0;

    public GamePlayerAdapter(Context context, ArrayList<int[]> arrows, ArrayList<String> matesName) {
        this.context = context;
        this.arrows = arrows;

        databaseHelper = new DatabaseHelper(context);
        mates = new ArrayList<>();
        ArrayList<Teammate> teammates = databaseHelper.getTeammates();
        for (Teammate mate : teammates) {
            if(matesName.contains(mate.getName())){
                mates.add(mate);
            }
        }
    }

    @NonNull
    @Override
    public GamePlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_game_player_item_3, parent, false);
        this.view = view;
        TextView tvPlayerName = view.findViewById(R.id.tvPlayerName);
        TextView arrow31 = view.findViewById(R.id.arrow31);
        TextView arrow32 = view.findViewById(R.id.arrow32);
        TextView arrow33 = view.findViewById(R.id.arrow33);

        holder = new GamePlayerHolder(view, context, arrows, this, tvPlayerName, arrow31, arrow32, arrow33);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GamePlayerHolder holder, int position) {

        holder.getTvPlayerName().setText(mates.get(position).getName());
        holder.getArrow31().setText("0");
        holder.getArrow32().setText("0");
        holder.getArrow33().setText("0");

    }


    @Override
    public int getItemCount() {
        return mates.size();
    }
}
