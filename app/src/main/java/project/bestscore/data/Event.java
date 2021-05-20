package project.bestscore.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Event {

    private String name;
    private String type;
    private String parkour;
    private LocalDateTime date;
    private ArrayList<Teammate> teammateList;
    private ArrayList<Target> targetList;
    private int id;////////////////////////
    
    public Event(String name, String type, String parkour, LocalDateTime date, ArrayList<Teammate> teammateList, ArrayList<Target> targetList) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.parkour = parkour;
        this.teammateList = teammateList;
        this.targetList = targetList;
    }

    public Event(String name, String type, LocalDateTime date, ArrayList<Teammate> teammateList, ArrayList<Target> targetList, int id) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.teammateList = teammateList;
        this.targetList = targetList;
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

    public String getParkour(){
        return parkour;
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