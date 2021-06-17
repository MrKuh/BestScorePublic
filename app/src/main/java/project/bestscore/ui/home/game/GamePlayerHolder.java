package project.bestscore.ui.home.game;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GamePlayerHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnTouchListener {

    private TextView tvArrow1;
    private TextView tvArrow2;
    private TextView tvArrow3;
    //private TextView tvArrow4;



    public GamePlayerHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
