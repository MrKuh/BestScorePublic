package project.bestscore.data;

public class Teammate {
    private String name;
    private int wins;
    private int id;

    public Teammate(String name, int wins) {
        this.name = name;
        this.wins = wins;
    }

    public Teammate(String name, int wins, int id) {
        this.name = name;
        this.wins = wins;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getId(){return id;}

    @Override
    public String toString() {
        return name + " hat " + wins + " Siege";
    }
}
