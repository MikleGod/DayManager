package com.example.mikle.daymanager.entity;

public enum Role {
    USER(1), ADMIN(2);
    private int id;
    Role(int id){
        this.id = id;
    }

    public static Role getRole(int role_id) {
        return role_id == 1 ? USER : ADMIN;
    }

    public int getId() {
        return id;
    }
}
