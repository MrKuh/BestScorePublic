package project.bestscore.ui.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.ArrayList;

import project.bestscore.R;
import project.bestscore.data.Event;
import project.bestscore.data.Parcour;

public class EventsFragment extends Fragment {

    private RecyclerView rvEvents;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events, container, false);

        ArrayList<Event> events = new ArrayList<>();

        Event event = new Event("Familienfeier", "Archery", new Parcour("Griasboch"),
                LocalDateTime.now(), null, null);

        events.add(event);
        events.add(event);

        rvEvents = root.findViewById(R.id.rvEvents);
        rvEvents.setHasFixedSize(true);
        rvEvents.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvEvents.setAdapter(new EventAdapter(events));

        return root;
    }
}