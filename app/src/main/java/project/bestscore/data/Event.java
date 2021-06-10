package project.bestscore.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Event implements Serializable {

    private String name;
    private String type;
    private Parcour parcour;
    private LocalDateTime date;
    private ArrayList<Teammate> teammateList;
    private ArrayList<Target> targetList;
    private int id = -1;
    
    public Event(String name, String type, Parcour parcour, LocalDateTime date, ArrayList<Teammate> teammateList, ArrayList<Target> targetList) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.parcour = parcour;
        this.teammateList = teammateList;
        this.targetList = targetList;
        this.parcour = parcour;
    }

    public Event(String name, String type, Parcour parcour, LocalDateTime date, ArrayList<Teammate> teammateList, ArrayList<Target> targetList, int id) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.teammateList = teammateList;
        this.targetList = targetList;
        this.id = id;
        this.parcour = parcour;
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
//test
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