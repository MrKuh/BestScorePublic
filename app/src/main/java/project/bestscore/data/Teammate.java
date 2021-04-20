package project.bestscore.data;

public class Teammate {
    private String name;
    private int wins;
    private int id = -1; //Brauche ich um zu schauen ob Teammate schon in der Datenbank ist

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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " hat " + wins + " Siege";
    }
}
