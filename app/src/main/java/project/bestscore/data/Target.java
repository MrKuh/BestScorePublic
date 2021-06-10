package project.bestscore.data;

public class Target {

    private int index;
    private String name;

    private int id;

    public Target(int number, String name) {
        this.index = number;
        this.name = name;
    }

    public Target(int number, String name, int id) {
        this.index = number;
        this.name = name;
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
