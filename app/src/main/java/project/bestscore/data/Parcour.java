package project.bestscore.data;

import java.io.Serializable;

public class Parcour implements Serializable {

    private String name;
    private int id = -1;

    public Parcour(String name){
        this.name = name;
    }

    public Parcour(String name, int id){
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
