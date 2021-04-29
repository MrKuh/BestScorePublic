package project.bestscore.ui.events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import project.bestscore.R;
import project.bestscore.data.Event;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private ArrayList<Event> events;

    public EventAdapter(ArrayList<Event> events){
        this.events = events;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event_item, parent, false);

        ImageView ivEventType = view.findViewById(R.id.ivEventType);
        TextView tvEventName = view.findViewById(R.id.tvEventName);
        TextView tvEventParkour = view.findViewById(R.id.tvEventName);
        TextView tvEventDate = view.findViewById(R.id.tvEventDate);

        EventViewHolder holder = new EventViewHolder(view, ivEventType, tvEventName, tvEventDate, tvEventParkour);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events.get(position);

        switch(event.getType()){
            case "Archery":
                holder.getIvEventType().setImageResource(R.drawable.ic_archery);
                break;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        holder.getTvEventDate().setText(event.getDate().format(dateTimeFormatter));
        holder.getTvEventName().setText(event.getName());
        holder.getTvEventParkour().setText(event.getParkour());

    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
