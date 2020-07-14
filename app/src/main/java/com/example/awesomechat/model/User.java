package com.example.awesomechat.model;

public class User {
    private String name;
    private String Email;
    private String Id;
    private int avatarMockUprecource;
    public User()
    {

    }
    public User(String name, String email, String id) {
        this.name = name;
        Email = email;
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getavatarMockUprecource() {
        return avatarMockUprecource;
    }

    public void setavatarMockUprecource(int name) {
        this.avatarMockUprecource = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
