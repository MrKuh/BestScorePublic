package project.bestscore.data;

public class Target {

    private int number;
    private String name;
    private Event event;

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
}
