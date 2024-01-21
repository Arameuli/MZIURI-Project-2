package org.example;

public class User {
    private String name;
    private String password;
    private String message;

    public User(String name, String password, String message) {
        this.name = name;
        this.password = password;
        this.message = message;
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getmessage() {
        return message;
    }
}
