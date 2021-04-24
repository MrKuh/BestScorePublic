package project.bestscore.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import project.bestscore.R;
import project.bestscore.data.DatabaseHelper;
import project.bestscore.data.Event;
import project.bestscore.data.Teammate;

public class HomeFragment extends Fragment {

    DatabaseHelper databaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        EditText etName = root.findViewById(R.id.etName);
        EditText etWins = root.findViewById(R.id.etWins);
        Button btnCommit = root.findViewById(R.id.btnCommit);
        TextView tvList = root.findViewById(R.id.tvList);

        databaseHelper = new DatabaseHelper(getActivity());

        ArrayList<Teammate> teammates = databaseHelper.getTeammates();

        tvList.setText(databaseHelper.getEvents().toString());

        btnCommit.setOnClickListener(v -> {

            Teammate teammate = new Teammate(etWins.getText().toString(),4);

            if(!databaseHelper.teammateInserted(teammate)){
                databaseHelper.insertTeammate(teammate);
                teammates.add(teammate);

                Toast.makeText(getContext(), "Inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Is already in database", Toast.LENGTH_SHORT).show();
            }

            Event event = new Event(etName.getText().toString(), "Golf", LocalDateTime.now(), teammates, null);

            if(databaseHelper.eventInserted(event)){
                Toast.makeText(getContext(), "Event Is already in database", Toast.LENGTH_SHORT).show();
            }

            databaseHelper.insertEvent(event);

            System.out.println(databaseHelper.getTeammates().toString());
            tvList.setText(databaseHelper.getEvents().toString());
        });

        return root;
    }
}