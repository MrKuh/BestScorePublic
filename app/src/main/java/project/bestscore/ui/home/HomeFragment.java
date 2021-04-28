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
        teammates.add(new Teammate("Peter", 5));
        teammates.add(new Teammate("Sepp", 7));

        teammates.forEach(teammate -> {
            if(!databaseHelper.teammateInserted(teammate)){
                databaseHelper.insertTeammate(teammate);
            }
        });

        tvList.setText(databaseHelper.getTeammates().toString());

        btnCommit.setOnClickListener(v -> {

            databaseHelper.deleteTeammate(teammates.get(0));

            tvList.setText(databaseHelper.getTeammates().toString());
        });

        return root;
    }
}