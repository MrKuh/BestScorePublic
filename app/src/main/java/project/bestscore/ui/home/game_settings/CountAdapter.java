package project.bestscore.ui.home.game_settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.bestscore.R;

public class CountAdapter extends RecyclerView.Adapter<CountViewHolder> {

    private ArrayList<String[]> zones;

    public CountAdapter(ArrayList<String[]> zones) {
        this.zones = zones;
    }

    @NonNull
    @Override
    public CountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_game_count_item, parent, false);

        TextView tvArrow = (TextView) view.findViewById(R.id.tvArrow);
        EditText txtZone1 = (EditText) view.findViewById(R.id.zone1);
        EditText txtZone2 = (EditText) view.findViewById(R.id.zone2);
        EditText txtZone3 = (EditText) view.findViewById(R.id.zone3);
        EditText txtZone4 = (EditText) view.findViewById(R.id.zone4);

        CountViewHolder holder = new CountViewHolder(view, tvArrow, txtZone1, txtZone2, txtZone3, txtZone4);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountViewHolder holder, int position) {
        String[] zone = zones.get(position);
        holder.getTvArrow().setText(zone[0]);
    }

    @Override
    public int getItemCount() {
        return zones.size();
    }
}
