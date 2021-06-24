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
import project.bestscore.ui.home.selection.player.TeammateSelectionAdapter;

public class ParcourSelectionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tvNameOfParcour;
    private ConstraintLayout clBackground;
    private ParcourSelection parcourSelection;
    private ParcourSelectionAdapter adapter;
    private boolean selected;
    private static final String TAG = "MyApp";


    public ParcourSelectionHolder(@NonNull View itemView, Context context, ParcourSelectionAdapter adapter, TextView tvNameOfParcour, ConstraintLayout clBackground, ParcourSelection parcourSelection) {
        super(itemView);
        this.tvNameOfParcour = tvNameOfParcour;
        this.clBackground = clBackground;
        this.parcourSelection = parcourSelection;
        this.adapter = adapter;
        selected = false;

        itemView.setOnClickListener(this);
    }

    public TextView getTvNameOfParcour() {
        return tvNameOfParcour;
    }

    @Override
    public void onClick(View v) {
        try{
            if (!selected && parcourSelection.getParcourSelected() == null) {
                selected = true;
                parcourSelection.addSelectedParcour((adapter.getParcourList().get(getAdapterPosition())));
                parcourSelection.setSelectionHolder(this);
                clBackground.setBackgroundResource(R.drawable.list_background_rounded_othercolor);
                parcourSelection.setBtnContinue(true);

            } else if (selected && parcourSelection.getParcourSelected().equals(adapter.getParcourList().get(getAdapterPosition()))) {
                selected = false;
                parcourSelection.deleteSelectedParcour();
                parcourSelection.setSelectionHolder(null);
                clBackground.setBackgroundResource(R.drawable.list_background_rounded);
                parcourSelection.setBtnContinue(false);
            } else {
                selected = true;
                parcourSelection.addSelectedParcour((adapter.getParcourList().get(getAdapterPosition())));
                parcourSelection.changeColor();
                parcourSelection.setSelectionHolder(this);
                clBackground.setBackgroundResource(R.drawable.list_background_rounded_othercolor);
                parcourSelection.setBtnContinue(true);
            }
        }catch (NullPointerException e){
            System.out.println("error");
        }

    }

    public ConstraintLayout getClBackground() {
        return clBackground;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

