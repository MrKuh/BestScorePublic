package project.bestscore.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import project.bestscore.ui.home.selection.parcour.Parcour;

public class Event {

    private String name;
    private String type;
    private Parcour parcour;
    private LocalDateTime date;
    private ArrayList<Teammate> teammateList;
    private ArrayList<Target> targetList;
    private int id;

    public Event(String name, String type, Parcour parcour, LocalDateTime date, ArrayList<Teammate> teammateList, ArrayList<Target> targetList) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.parcour = parcour;
        this.teammateList = teammateList;
        this.targetList = targetList;
    }

    public Event(String name, String type, Parcour parcour ,LocalDateTime date, ArrayList<Teammate> teammateList, ArrayList<Target> targetList, int id) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.teammateList = teammateList;
        this.targetList = targetList;
        this.parcour = parcour;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ArrayList<Teammate> getTeammateList() {
        return teammateList;
    }

    public ArrayList<Target> getTargetList() {
        return targetList;
    }

    public Parcour getParcour(){
        return parcour;
    }

    public int getId(){return id;}

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", teammateList=" + teammateList +
                ", targetList=" + targetList +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }
}