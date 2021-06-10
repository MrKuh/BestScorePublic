package project.bestscore.ui.home.selection.parcour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import project.bestscore.R;
import project.bestscore.data.Parcour;
import project.bestscore.ui.home.game_settings.GameSettingActivity;

public class ParcourSelectionHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView tvNameOfParcour;
    private ParcourSelectionAdapter adapter;
    private Activity activity;


    private boolean selected;


    public ParcourSelectionHolder(@NonNull View itemView, Activity activity, ParcourSelectionAdapter adapter, TextView tvNameOfParcour) {
        super(itemView);
        this.tvNameOfParcour = tvNameOfParcour;
        this.adapter = adapter;
        this.activity = activity;

        itemView.setOnClickListener(this);
    }

    public TextView getTvNameOfParcour() {
        return tvNameOfParcour;
    }

    @Override
    public void onClick(View v) {
        Intent intent = activity.getIntent();//new Intent(activity, GameSettingActivity.class);
        intent.putExtra(GameSettingActivity.getExtraParcourName(), adapter.getParcour(getAdapterPosition()));
        activity.setResult(GameSettingActivity.getRequestCodeParcour(), intent);
        activity.finish();
    }

}
