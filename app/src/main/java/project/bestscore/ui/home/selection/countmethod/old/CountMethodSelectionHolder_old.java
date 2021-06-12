package project.bestscore.ui.home.selection.countmethod.old;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import project.bestscore.data.Parcour;

public class CountMethodSelectionHolder_old extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnTouchListener {
    private TextView tvNameOfParcour;
    private CountMethodSelectionAdapter_old adapter;
    private final GestureDetectorCompat mGestureDetector;
    private static final String TAG = "MyApp";


    public CountMethodSelectionHolder_old(@NonNull View itemView, Context context, CountMethodSelectionAdapter_old adapter, TextView tvNameOfPlayer) {
        super(itemView);
        this.tvNameOfParcour = tvNameOfPlayer;
        this.adapter = adapter;

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
        System.out.println("Hier passiert nichts");
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
