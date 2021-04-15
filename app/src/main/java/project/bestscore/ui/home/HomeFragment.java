package project.bestscore.ui.home;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.w3c.dom.Text;

import java.time.LocalDate;

import project.bestscore.MainActivity;
import project.bestscore.R;
import project.bestscore.data.DatabaseHelper;
import project.bestscore.data.Event;
import project.bestscore.data.Event_Archery;
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

        btnCommit.setOnClickListener(v -> {

            databaseHelper.insertTeammate(new Teammate(etName.getText().toString(),
                    Integer.valueOf(etWins.getText().toString())));

            Event_Archery event = new Event_Archery(LocalDate.now(), databaseHelper.getTeammates());

            databaseHelper.insertEvent(event);

            tvList.setText(databaseHelper.getEvents().toString());
        });

        return root;
    }
}