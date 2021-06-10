package project.bestscore.ui.parcours_old;

public class Parcour2_old {
    private String parcourName;
    private int id = -1;

    public Parcour2_old(String parcourName) {
        this.parcourName = parcourName ;
    }

    public Parcour2_old(String parcourName, int id) {
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
