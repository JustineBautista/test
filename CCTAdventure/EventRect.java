package CCTAdventure;

import java.awt.*;

public class EventRect extends Rectangle {

    private int eventRectDefaultX, eventRectDefaultY;
    private boolean eventDone = false;

    public int getEventRectDefaultX() {
        return eventRectDefaultX;
    }

    public EventRect setEventRectDefaultX(int eventRectDefaultX) {
        this.eventRectDefaultX = eventRectDefaultX;
        return this;
    }

    public int getEventRectDefaultY() {
        return eventRectDefaultY;
    }

    public EventRect setEventRectDefaultY(int eventRectDefaultY) {
        this.eventRectDefaultY = eventRectDefaultY;
        return this;
    }

    public boolean isEventDone() {
        return eventDone;
    }

    public EventRect setEventDone(boolean eventDone) {
        this.eventDone = eventDone;
        return this;
    }
}
