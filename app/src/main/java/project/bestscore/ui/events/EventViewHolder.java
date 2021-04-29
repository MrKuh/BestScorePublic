package project.bestscore.ui.events;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder{

    private ImageView ivEventType;
    private TextView tvEventName;
    private TextView tvEventDate;
    private TextView tvEventParkour;

    public EventViewHolder(@NonNull View itemView, ImageView ivEventType, TextView tvEventName, TextView tvEventDate, TextView tvEventParkour) {

        super(itemView);

        this.ivEventType = ivEventType;
        this.tvEventDate = tvEventDate;
        this.tvEventName = tvEventName;
        this.tvEventParkour = tvEventParkour;
    }

    public ImageView getIvEventType() {
        return ivEventType;
    }

    public TextView getTvEventName() {
        return tvEventName;
    }

    public TextView getTvEventDate() {
        return tvEventDate;
    }

    public TextView getTvEventParkour() {
        return tvEventParkour;
    }
}
