package project.bestscore.data;

public class CountMethod {

    public int id;
    public String name;

    public CountMethod(String name) {
        this.name = name;
    }

    public CountMethod(String name, int id) {
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
}
