package com.bof.devfest18.danaevent.models;

public class Event {

    private String id;
    private String title;
    private String place;
    private String background;

    private int event_id;
    private String event_name;
    private int attending_count;



    public Event(String id, String title, String place, String background) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.background = background;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
