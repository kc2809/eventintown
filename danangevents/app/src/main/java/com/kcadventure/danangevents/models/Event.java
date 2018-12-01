package com.kcadventure.danangevents.models;

import java.io.Serializable;
import java.util.List;

public class Event implements Serializable {

    private int event_id;
    private String event_name;
    private int attending_count;
    private int category_id;
    private String description;
    private String start_time;
    private String end_time;
    private double cost;
    private double lat;
    private double lon;
    private List<User_Event> users;
    private List<Comment> comments;
    private String image;
    private String location;

    public Event() {

    }

    public Event(int event_id, String event_name, int attending_count, int category_id,
        String description, String start_time, String end_time, double cost, double lat, double lon,
        String image) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.attending_count = attending_count;
        this.category_id = category_id;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.cost = cost;
        this.lat = lat;
        this.lon = lon;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Event{" +
                   "event_id=" + event_id +
                   ", event_name='" + event_name + '\'' +
                   ", attending_count=" + attending_count +
                   ", category_id=" + category_id +
                   ", description='" + description + '\'' +
                   ", start_time='" + start_time + '\'' +
                   ", end_time='" + end_time + '\'' +
                   ", cost=" + cost +
                   ", lat=" + lat +
                   ", lon=" + lon +
                   ", image='" + image + '\'' +
                   '}';
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public int getAttending_count() {
        return attending_count;
    }

    public void setAttending_count(int attending_count) {
        this.attending_count = attending_count;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<User_Event> getUsers() {
        return users;
    }

    public void setUsers(List<User_Event> users) {
        this.users = users;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
