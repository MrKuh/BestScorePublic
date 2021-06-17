package project.bestscore.ui.home.selection.countmethod;

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
import project.bestscore.data.CountMethod;
import project.bestscore.data.Parcour;

public class CountMethodSelectionHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnTouchListener {
    private TextView tvNameOfCountMethod;
    private ConstraintLayout clBackground;
    private CountMethodSelection countMethodSelection;
    private CountMethodSelectionAdapter adapter;
    private boolean selected;
    private final GestureDetectorCompat mGestureDetector;
    private static final String TAG = "MyApp";


    public CountMethodSelectionHolder(@NonNull View itemView, Context context, CountMethodSelectionAdapter adapter, TextView tvNameOfCountMethod, ConstraintLayout clBackground, CountMethodSelection countMethodSelection) {
        super(itemView);
        this.tvNameOfCountMethod = tvNameOfCountMethod;
        this.clBackground = clBackground;
        this.countMethodSelection = countMethodSelection;
        this.adapter = adapter;
        selected = false;

        MyGestureListener mgl = new MyGestureListener();
        mGestureDetector = new GestureDetectorCompat(context, mgl);
        itemView.setOnTouchListener(this);
        itemView.setOnClickListener(this);
    }

    public TextView getTvNameOfCountMethod() {
        return tvNameOfCountMethod;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        if(!selected && countMethodSelection.getCountMethodsSelected().size() < 1){
            selected = true;
            countMethodSelection.addSelectedCountMethod(adapter.getCountMethodList().get(getAdapterPosition()));
            clBackground.setBackgroundResource(R.drawable.list_background_rounded_othercolor);
        }else{
            selected = false;
            countMethodSelection.deleteSelectedCountMethod(countMethodSelection.getCountMethodSelected());
            countMethodSelection.deleteSelectedCountMethod(adapter.getCountMethodList().get(getAdapterPosition()));
            clBackground.setBackgroundResource(R.drawable.list_background_rounded);
        }
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
                    CountMethod countMethod = adapter.getCountMethodList().get(getAdapterPosition());
                    adapter.getDatabaseHelper().deleteCountMethod(countMethod);
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

