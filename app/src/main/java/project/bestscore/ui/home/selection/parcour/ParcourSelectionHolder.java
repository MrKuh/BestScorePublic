package project.bestscore.ui.home.selection.parcour;

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

import java.util.ArrayList;

import project.bestscore.R;
import project.bestscore.data.Parcour;
import project.bestscore.data.Teammate;
import project.bestscore.ui.home.selection.player.TeammateSelection;

public class ParcourSelectionHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnTouchListener {
    private TextView tvNameOfParcour;
    private ParcourSelectionAdapter adapter;
    private final GestureDetectorCompat mGestureDetector;
    private ConstraintLayout clBackground;
    private ParcourSelection parcourSelection;
    private static final String TAG = "MyApp";

    private ArrayList<Teammate> teammatesSelected = new ArrayList<Teammate>();

    private boolean selected;


    public ParcourSelectionHolder(@NonNull View itemView, Context context, ParcourSelectionAdapter adapter, TextView tvNameOfPlayer) {
        super(itemView);
        this.tvNameOfParcour = tvNameOfPlayer;
        this.adapter = adapter;
        selected = true;

        MyGestureListener mgl = new MyGestureListener();
        mGestureDetector = new GestureDetectorCompat(context, mgl);
        itemView.setOnTouchListener(this);
        itemView.setOnClickListener(this);
    }

    public TextView getTvNameOfParcour() {
        return tvNameOfParcour;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        if(selected){
            selected = false;
            clBackground.setBackgroundResource(R.drawable.list_background_rounded_othercolor);
        }else{
            selected = true;
            //teammateSelection.deleteSelectedTeammate(adapter.getTeammateList().get(getAdapterPosition()));
            clBackground.setBackgroundResource(R.drawable.list_background_rounded);
        }
    }

    private class MyGestureListener
            extends GestureDetector.SimpleOnGestureListener {
        // Minimal and Maximal swipe distance.
        private final int MIN_DIST = 100;
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
                    Parcour parcour = adapter.getParcourList().get(getAdapterPosition());
                    //adapter.getDatabaseHelper().deleteParcour(parcour);
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
