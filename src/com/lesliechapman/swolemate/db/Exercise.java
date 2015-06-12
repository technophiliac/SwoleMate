package com.lesliechapman.swolemate.db;

/**
 * Created by LChapm001 on 6/12/2015.
 */
public class Exercise {
    private long id;
    private String displayName;

    private String muscleGroup;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return displayName + " (" + muscleGroup + ")";
    }
}