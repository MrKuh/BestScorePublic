package project.bestscore.data;

public class Teammate_Event {
    private int teammate_id;
    private int event_id;

    public Teammate_Event(int teammate_id, int event_id){
        this.teammate_id = teammate_id;
        this.event_id = event_id;
    }

    public int getTeammate_id() {
        return teammate_id;
    }

    public int getEvent_id() {
        return event_id;
    }
}
