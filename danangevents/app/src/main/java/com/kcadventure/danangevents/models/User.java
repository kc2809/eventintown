package com.kcadventure.danangevents.models;

import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;

public class User {

    private int user_id;
    private String name;
    private String role;
    private String email;
    private String password;

    public User() {

    }

    public User(int user_id, String name, String role, String email, String password) {
        this.user_id = user_id;
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("user_id", user_id);
        result.put("name", name);
        result.put("role", role);
        result.put("email", email);
        result.put("password", password);

        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                   "user_id=" + user_id +
                   ", name='" + name + '\'' +
                   ", role='" + role + '\'' +
                   ", email='" + email + '\'' +
                   ", password='" + password + '\'' +
                   '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
