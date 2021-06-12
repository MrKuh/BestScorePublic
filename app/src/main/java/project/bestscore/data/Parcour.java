package project.bestscore.data;

public class Parcour {

    public int id;
    public String name;

    public Parcour(String name) {
        this.name = name;
    }

    public Parcour(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getParcourName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
