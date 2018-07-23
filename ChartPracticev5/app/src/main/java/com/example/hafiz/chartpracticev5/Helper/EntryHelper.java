package com.example.hafiz.chartpracticev5.Helper;

import android.support.v7.app.AppCompatActivity;

import com.example.hafiz.chartpracticev5.Model.HabitCard;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Calendar;

public class EntryHelper{
    Calendar calender = Calendar.getInstance();
    int day = calender.get(Calendar.DAY_OF_YEAR);


    public int checkEntryExist(ArrayList<HabitCard> habitCardArrayList){
        ArrayList<Entry> entries = new ArrayList<>();

        if(day ==){
            habitCardArrayList.get(day)

        }
    }

    public Entry createEntry(){

        Entry currentDay = new Entry((float)day, );
    }

    public Entry addToEntry(){

    }






}
//we need to figure about years, if a year changes the Calender.DAY_OF_YEAR will see the same values, and that's a no go.
