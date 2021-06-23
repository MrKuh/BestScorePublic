package project.bestscore.ui.home.game;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import project.bestscore.R;

public class GamePlayerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView tvPlayerName;
    private TextView arrow31;
    private TextView arrow32;
    private TextView arrow33;
    //private TextView arrow44;
    private Context context;

    public GamePlayerHolder(@NonNull View itemView, Context context, TextView tvPlayerName, TextView arrow31, TextView arrow32, TextView arrow33) {
        super(itemView);
        this.tvPlayerName = tvPlayerName;
        this.arrow31 = arrow31;
        this.arrow32 = arrow32;
        this.arrow33 = arrow33;
        this.context = context;

        this.arrow31.setOnClickListener(this);
        this.arrow32 .setOnClickListener(this);
        this.arrow33 .setOnClickListener(this);
    }

    public TextView getTvPlayerName() {
        return tvPlayerName;
    }

    public TextView getArrow31() {
        return arrow31;
    }

    public TextView getArrow32() {
        return arrow32;
    }

    public TextView getArrow33() {
        return arrow33;
    }

    @Override
    public void onClick(View v) {
        System.out.println("Nice");
    }
}
