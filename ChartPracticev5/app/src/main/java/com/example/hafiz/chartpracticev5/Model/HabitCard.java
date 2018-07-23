package com.example.hafiz.chartpracticev5.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by hafiz on 7/7/2018.
 */


//Just one for now.
public class HabitCard{
    private String habitName;
    private double habitUserMinutesTotal;




    private HashMap<Integer, Double> habitMinutesHashMap = new HashMap<>();//an array list with minutes, with each day being the.. index.
    Calendar calendar = Calendar.getInstance();
    //int day = calendar.get(Calendar.DAY_OF_YEAR);

    public HashMap addToHabitToday(double minutes) {
        int day = calendar.get(Calendar.DAY_OF_YEAR);

        if (habitMinutesHashMap.containsKey(day)) {
            double value = habitMinutesHashMap.get(day);
            habitMinutesHashMap.put(day, (value + minutes));
        } else {

            habitMinutesHashMap.put(day, minutes);
        }
        return habitMinutesHashMap;
    }

    public HashMap<Integer, Double> getHabitMinutesHashMap() {
        return habitMinutesHashMap;
    }
    public void setHabitMinutesHashMap(HashMap<Integer, Double> habitMinutesHashMap) {
        this.habitMinutesHashMap = habitMinutesHashMap;
    }


    public HabitCard(String habitName) {
        this.habitName = habitName;
    }

    public HabitCard(double habitUserMinutesTotal) {
        this.habitUserMinutesTotal = habitUserMinutesTotal;
    }

    public void addMinutes(double minutes){
        habitMinutesHashMap.put();
    }


    public HabitCard(String habitName, double habitUserMinutesTotal){
        this.habitName = habitName;
        this.habitUserMinutesTotal = habitUserMinutesTotal;
    }

    public double getHabitUserMinutesTotal() {
        return this.habitUserMinutesTotal;
    }

    public void setHabitUserMinutesTotal(double habitUserMinutesTotal) {
        this.habitUserMinutesTotal = habitUserMinutesTotal;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }
}
