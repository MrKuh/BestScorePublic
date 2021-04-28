package project.bestscore.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GameHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView imgBow;
    private TextView tvName;

    public GameHolder(@NonNull View itemView, TextView tvName, ImageView imgBow) {
        super(itemView);
        this.imgBow = imgBow;
        this.tvName = tvName;
        itemView.setOnClickListener(v -> {

            System.out.println(tvName.getText() + ": ausgewählt");

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
