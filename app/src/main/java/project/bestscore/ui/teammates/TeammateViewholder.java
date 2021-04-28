package project.bestscore.ui.teammates;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeammateViewholder extends RecyclerView.ViewHolder {
    private TextView tvNameOfPlayer;
    private TextView tvWinsOfPlayer;


    public TeammateViewholder(@NonNull View itemView, Activity activity, TeammateAdapter adapter, Context context, TextView tvNameOfPlayer, TextView tvWinsOfPlayer) {
        super(itemView);
        this.tvNameOfPlayer = tvNameOfPlayer;
        this.tvWinsOfPlayer = tvWinsOfPlayer;
    }

    public TextView getTvNameOfPlayer() {
        return tvNameOfPlayer;
    }

    public TextView getTvWinsOfPlayer() {
        return tvWinsOfPlayer;
    }
}
