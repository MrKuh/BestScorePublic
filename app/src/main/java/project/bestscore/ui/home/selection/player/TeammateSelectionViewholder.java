package project.bestscore.ui.home.selection.player;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import project.bestscore.R;
import project.bestscore.data.Teammate;

public class TeammateSelectionViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tvNameOfPlayer;
    private TextView tvWinsOfPlayer;
    private ConstraintLayout clBackground;
    private TeammateSelection teammateSelection;
    private TeammateSelectionAdapter adapter;
    private boolean selected;
    private static final String TAG = "MyApp";


    public TeammateSelectionViewholder(@NonNull View itemView, Context context, TeammateSelectionAdapter adapter, TextView tvNameOfPlayer, TextView tvWinsOfPlayer, ConstraintLayout clBackground, TeammateSelection teammateSelection) {
        super(itemView);
        this.tvNameOfPlayer = tvNameOfPlayer;
        this.tvWinsOfPlayer = tvWinsOfPlayer;
        this.clBackground = clBackground;
        this.teammateSelection = teammateSelection;
        this.adapter = adapter;
        selected = true;

        itemView.setOnClickListener(this);
    }

    public TextView getTvNameOfPlayer() {
        return tvNameOfPlayer;
    }

    public TextView getTvWinsOfPlayer() {
        return tvWinsOfPlayer;
    }

    @Override
    public void onClick(View v) {
        if(selected){
            selected = false;
            teammateSelection.addSelectedTeammate(adapter.getTeammateList().get(getAdapterPosition()));
            clBackground.setBackgroundResource(R.drawable.list_background_rounded_othercolor);
        }else{
            selected = true;
            teammateSelection.deleteSelectedTeammate(adapter.getTeammateList().get(getAdapterPosition()));
            clBackground.setBackgroundResource(R.drawable.list_background_rounded);
        }
    }

    public ConstraintLayout getClBackground() {
        return clBackground;
    }

    private class MyGestureListener
            extends GestureDetector.SimpleOnGestureListener {
        // Minimal and Maximal swipe distance.
        private final int MIN_DIST = 70;
        private final int MAX_DIST = 1000;
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            float deltaX = e1.getX() - e2.getX();
            float deltaY = e1.getY() - e2.getY();
            float deltaXAbs = Math.abs(deltaX);
            float deltaYAbs = Math.abs(deltaY);
            if (deltaXAbs > MIN_DIST && deltaXAbs < MAX_DIST) {
                if (deltaX > 0) {
                    Log.i(TAG, "swipe left");
                } else {
                    Log.i(TAG, "swipe right");
                    Teammate mate = adapter.getTeammateList().get(getAdapterPosition());
                    adapter.getDatabaseHelper().deleteTeammate(mate);
                    adapter.updateList();
                    adapter.notifyDataSetChanged();
                }
            }
            if (deltaYAbs > MIN_DIST && deltaXAbs < MAX_DIST) {
                if (deltaY > 0) {
                    Log.i(TAG, "swipe up");
                } else {
                    Log.i(TAG, "swipe down");
                }
            }
            return false;
        }
    }
}
