package com.sefrWahed.apeye.models;

/**
 * Created by Abdel-Rahman Elshikh
 */
public class Plant {
    private String name;
    private int resoucseId;

    public Plant(String name, int resoucseId) {
        this.name = name;
        this.resoucseId = resoucseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResoucseId() {
        return resoucseId;
    }

    public void setResoucseId(int resoucseId) {
        this.resoucseId = resoucseId;
    }
}
