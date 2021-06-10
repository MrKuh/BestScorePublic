package project.bestscore.ui.home.selection.parcour;

public class Parcour {

    public int id;
    public String name;

    public Parcour(String name) {
        this.name = name;
    }

    public Parcour(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}
