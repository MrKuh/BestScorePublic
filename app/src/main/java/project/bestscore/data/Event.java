package project.bestscore.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public abstract class Event {

    private String type;
    private LocalDate date;
    private ArrayList<Teammate> teammateList;
    private ArrayList<Target> targetList;
    
    public Event(String type, LocalDate date, ArrayList<Teammate> teammateList, ArrayList<Target> targetList) {
        this.type = type;
        this.date = date;
        this.teammateList = teammateList;
        this.targetList = targetList;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public ArrayList<Teammate> getTeammateList() {
        return teammateList;
    }

    public ArrayList<Target> getTargetList() {
        return targetList;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                ", date=" + date +
                ", teammateList=" + teammateList +
                '}';
    }
}
