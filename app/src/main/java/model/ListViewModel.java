package model;

import java.util.List;

/**
 * Created by Batman on 3/25/16.
 */
public class ListViewModel {

    private String eventName;
    private String eventDate;
    private List<String> eventNamesList;
    private List<String> eventDatesList;

    public List<String> getEventNamesList() {
        return eventNamesList;
    }

    public void setEventNamesList(List<String> eventNamesList) {
        this.eventNamesList = eventNamesList;
    }

    public List<String> getEventDatesList() {
        return eventDatesList;
    }

    public void setEventDatesList(List<String> eventDatesList) {
        this.eventDatesList = eventDatesList;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
