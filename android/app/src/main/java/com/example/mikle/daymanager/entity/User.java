package com.example.mikle.daymanager.entity;

import java.util.Objects;

public class User
{
    private Role role;
    private String nickname;
    private int id;

    public User(Role role, String nickname) {
        this.role = role;
        this.nickname = nickname;
    }

    public User(Role role, String nickname, int id) {
        this.role = role;
        this.nickname = nickname;
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
        User user = (User) o;
        return id == user.id &&
                role == user.role &&
                Objects.equals(nickname, user.nickname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(role, nickname, id);
    }
}
