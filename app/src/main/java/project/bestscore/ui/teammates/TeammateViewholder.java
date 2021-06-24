package project.bestscore.ui.teammates;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import project.bestscore.data.Teammate;

public class TeammateViewholder extends RecyclerView.ViewHolder {
    private TextView tvNameOfPlayer;
    private TextView tvWinsOfPlayer;
    private TeammateAdapter adapter;
    private static final String TAG = "MyApp";


    public TeammateViewholder(@NonNull View itemView, Context context, TeammateAdapter adapter, TextView tvNameOfPlayer, TextView tvWinsOfPlayer) {
        super(itemView);
        this.tvNameOfPlayer = tvNameOfPlayer;
        this.tvWinsOfPlayer = tvWinsOfPlayer;
        this.adapter = adapter;

    }

    public TextView getTvNameOfPlayer() {
        return tvNameOfPlayer;
    }

    public TextView getTvWinsOfPlayer() {
        return tvWinsOfPlayer;
    }


}
