package com.example.quiz;

public class LevelModel {

    private String name;
    //private int grids;

    /*public LevelModel() {
        //for firebase
    }*/

    public LevelModel(String name) {
        this.name = name;
        //this.grids = grids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public int getGrids() {
        return grids;
    }

    public void setGrids(int grids) {
        this.grids = grids;
    }*/
}
