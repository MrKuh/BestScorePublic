package project.bestscore.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        ArrayList<Teammate> teammates = new ArrayList<>();
        teammates.add(new Teammate("Peter", 4));
        teammates.add(new Teammate("Sepp", 1));

        //for(int i = 0; i < teammates.size(); i++){
            //databaseHelper.insertTeammate(teammates.get(i));
        //}

        btnCommit.setOnClickListener(v -> {

            teammates.add(new Teammate(etWins.getText().toString(),4));
            Event event = new Event(etName.getText().toString(), "Golf", LocalDateTime.now(), teammates, null);
            databaseHelper.insertEvent(event);

            System.out.println(databaseHelper.getTeammates().toString());
            tvList.setText(databaseHelper.getEvents().toString());
        });

        return root;
    }
}