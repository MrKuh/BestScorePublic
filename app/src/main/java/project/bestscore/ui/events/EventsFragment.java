package project.bestscore.ui.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

import project.bestscore.R;
import project.bestscore.data.Event;

public class EventsFragment extends Fragment {

    private RecyclerView rvEvents;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events, container, false);

        ArrayList<Event> events = new ArrayList<>();

        Event event = new Event("Familienfeier", "Archery", "Griasboch",
                LocalDateTime.now(), null, null);

        events.add(event);

        rvEvents = root.findViewById(R.id.rvEvents);
        rvEvents.setHasFixedSize(true);
        rvEvents.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvEvents.setAdapter(new EventAdapter(events));

        return root;
    }
}