package project.bestscore.ui.parcours;

public class Parcour {
    private String parcourName;
    private int id = -1; //Brauche ich um zu schauen ob Teammate schon in der Datenbank ist

    public Parcour(String parcourName) {
        this.parcourName = parcourName ;
    }

    public Parcour(String parcourName, int id) {
        this.parcourName = parcourName ;
        this.id = id;
    }

    public String getParcourName() {
        return parcourName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setParcourName(String parcourName) {
        this.parcourName = parcourName;
    }

    @Override
    public String toString() {
        return parcourName;
    }
}
