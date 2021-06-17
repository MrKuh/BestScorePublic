package project.bestscore.data;

import java.util.ArrayList;

public class CountMethod {

    private int id;
    private String name;
    private ArrayList<int[]> arrows = new ArrayList<int[]>();
    private int arrowAmount = 1;

    public CountMethod(String name) {
        int[] zones = new int[4];
        zones[0] = 20;
        zones[1] = 18;
        zones[2] = 16;
        zones[3] = 12;
        arrows.add(zones);
        zones[0] = 10;
        zones[1] = 8;
        zones[2] = 6;
        zones[3] = 2;
        arrows.add(zones);

        this.name = name;
    }

    public CountMethod(String name, int id) {
        int[] zones = new int[4];
        zones[0] = 20;
        zones[1] = 18;
        zones[2] = 16;
        zones[3] = 12;
        arrows.add(zones);
        zones[0] = 10;
        zones[1] = 8;
        zones[2] = 6;
        zones[3] = 2;
        arrows.add(zones);

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getCountMethodName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    public ArrayList<int[]> getArrows() {
        return arrows;
    }

    public int getArrowAmount() {
        return arrowAmount;
    }
}
