package project.bestscore.ui.teammates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import project.bestscore.R;

public class TeammateAdapter extends RecyclerView.Adapter<TeammateViewholder> {




    @NonNull
    @Override
    public TeammateViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_teammates_item,parent,false);
        TextView tvNameOfPlayer = view.findViewById(R.id.tvNameOfPlayer);
        TextView tvWinsOfPlayer = view.findViewById(R.id.tvWinsOfPlayer);
    }

    @Override
    public void onBindViewHolder(@NonNull TeammateViewholder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
