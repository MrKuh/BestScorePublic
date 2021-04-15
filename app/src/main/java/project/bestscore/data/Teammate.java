package project.bestscore.data;

public class Teammate {
    private String name;
    private int wins;

    public Teammate(String name, int wins) {
        this.name = name;
        this.wins = wins;
    }


    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    @Override
    public String toString() {
        return name + " hat " + wins + " Siege";
    }
}
