package com.example.mikle.daymanager.entity;

import java.util.Objects;

public class UserPrivates {

    private String mail;
    private int password;
    private int id;

    public UserPrivates(String mail, int password, int id) {
        this.mail = mail;
        this.password = password;
        this.id = id;
    }

    public UserPrivates(String mail, String password, int id) {
        this.mail = mail;
        this.password = password.hashCode();
        this.id = id;
    }

    public UserPrivates(String mail, int password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrivates that = (UserPrivates) o;
        return password == that.password &&
                id == that.id &&
                Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mail, password, id);
    }
}
