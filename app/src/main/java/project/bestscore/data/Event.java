package project.bestscore.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public abstract class Event {

    private String type;
    private LocalDate date;
    private ArrayList<Teammate> teammateList;
    private ArrayList<Target> targetList;
    private int id;
    
    public Event(String type, LocalDate date, ArrayList<Teammate> teammateList, ArrayList<Target> targetList) {
        this.type = type;
        this.date = date;
        this.teammateList = teammateList;
        this.targetList = targetList;
    }

    public Event(String type, LocalDate date, ArrayList<Teammate> teammateList, ArrayList<Target> targetList, int id) {
        this.type = type;
        this.date = date;
        this.teammateList = teammateList;
        this.targetList = targetList;
        this.id = id;
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

    public int getid(){return id;}

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                ", date=" + date +
                ", teammateList=" + teammateList +
                ", id=" + id +
                '}';
    }
}
