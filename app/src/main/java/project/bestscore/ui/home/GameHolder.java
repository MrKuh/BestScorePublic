package project.bestscore.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import project.bestscore.ui.parcours.ParcourActivity;

public class GameHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView imgBow;
    private TextView tvName;

    public GameHolder(@NonNull View itemView, TextView tvName, ImageView imgBow, Context context) {
        super(itemView);
        this.imgBow = imgBow;
        this.tvName = tvName;
        itemView.setOnClickListener(v -> {

            System.out.println(tvName.getText() + ": ausgewählt");

            Intent i = new Intent(context, ParcourActivity.class);
            context.startActivity(i);

        });
    }

    public ImageView getImgBow() {
        return imgBow;
    }

    public void setImgBow(ImageView imgBow) {
        this.imgBow = imgBow;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    @Override
    public void onClick(View v) {

    }
}
