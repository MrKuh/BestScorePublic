package project.bestscore.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Event_Archery extends Event{

    public Event_Archery(LocalDate date, ArrayList<Teammate> teammateList,
                         ArrayList<Target> targetList) {
        super("Archery", date, teammateList, targetList);
    }

    /*public Event_Archery(LocalDate date, ArrayList<Teammate> teammateList, int id) {
        super("Archery", date, teammateList, null, id);
    }*/

    public Event_Archery(LocalDate date, ArrayList<Teammate> teammateList) {
        super("Archery", date, teammateList, null);
    }
}
