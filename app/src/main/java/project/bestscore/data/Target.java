package project.bestscore.data;

import java.io.Serializable;

public class Target implements Serializable {

    private int number;
    private String name;
    private Event event;
    private int id = -1;

    public Target(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getId(){return id;}
}
