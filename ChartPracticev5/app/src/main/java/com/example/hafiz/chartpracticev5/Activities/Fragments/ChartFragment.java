package com.example.hafiz.chartpracticev5.Activities.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hafiz.chartpracticev5.Helper.DayAxisValueFormatter;
import com.example.hafiz.chartpracticev5.Model.HabitCard;
import com.example.hafiz.chartpracticev5.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ChartFragment extends Fragment {
    View view;
    LineChart lineChart;
    ArrayList<HabitCard> habitCards;
    SharedPreferences mPrefs;
    DayAxisValueFormatter dayAxisValueFormatter;
    LineDataSet dataSet;
    HabitCard genericHabitCard = new HabitCard("genericHabit", 0);



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chart_fragment_activity, container, false);
        lineChart = (LineChart) view.findViewById(R.id.LineChart);
        habitCards = loadData();
        ArrayList<Entry> entries = new ArrayList<Entry>();
        /*ArrayList<Entry> Habit0 = new ArrayList<Entry>();//Each one a new habit
        ArrayList<Entry> Habit1 = new ArrayList<Entry>();
        ArrayList<Entry> Habit2 = new ArrayList<Entry>();
        ArrayList<Entry> Habit3 = new ArrayList<Entry>();
        ArrayList<Entry> Habit4 = new ArrayList<Entry>();
        ArrayList<Entry> Habit5 = new ArrayList<Entry>();*/





        for (int i = 0; i < habitCards.size(); i++) {//This is now irrelevant.
            //habitCards = loadData();
            if (habitCards != null && habitCards.size() != 0) {
                float userMinutes = (float)habitCards.get(i).getHabitUserMinutesTotal();
                float f = (float) i;
                // turn your data into Entry objects
                entries.add(new Entry(f, userMinutes));
            } else {
                break;
            }
        }

        dataSet = new LineDataSet(entries, "Habits"); // add entries to dataset

        for(int i = 0; i <habitCards.size(); i++){
            if (habitCards != null && habitCards.size() != 0){
                String habitName = habitCards.get(i).getHabitName();
                ArrayList<Double> habitMinutesArray = habitCards.get(i).getHabitMinutesHashMap();

            }
        }

        dataSet = new LineDataSet(entries, "Habits"); // add entries to dataset

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        //dataSet.setColor(...);//are these neccesary right now?
        //dataSet.setValueTextColor(...); // styling, ...

        XAxis xAxis = lineChart.getXAxis();
        //xAxis.setValueFormatter(new DayAxisValueFormatter(lineChart).getFormattedValue(xAxis, ));




        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate(); // refresh

        return view;
    }

    @Override
    public void onResume() {
        //view = inflater.inflate(R.layout.chart_fragment_activity, container, false);
        lineChart = (LineChart) view.findViewById(R.id.LineChart);
        habitCards = loadData();
        ArrayList<Entry> entries = new ArrayList<Entry>();



        for (int i = 0; i < habitCards.size(); i++) {
            //habitCards = loadData();
            if (habitCards != null && habitCards.size() != 0) {
                float userMinutes = (float)habitCards.get(i).getHabitUserMinutesTotal();
                float f = (float) i;
                // turn your data into Entry objects
                entries.add(new Entry(f, userMinutes));
            } else {
                break;
            }
        }

        dataSet = new LineDataSet(entries, "Habits"); // add entries to dataset

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        //dataSet.setColor(...);//are these neccesary right now?
        //dataSet.setValueTextColor(...); // styling, ...

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate(); // refresh

        super.onResume();
    }

    private ArrayList<HabitCard> loadData() {
        mPrefs = getActivity().getSharedPreferences("HabitSharedPref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("HabitCardAL", null);
        Type type = new TypeToken<ArrayList<HabitCard>>() {
        }.getType();
        habitCards = gson.fromJson(json, type);


        if (habitCards == null) {
            habitCards = new ArrayList<HabitCard>();
            habitCards.add(genericHabitCard);
        }
        return habitCards;
    }

    private void saveData() {
        mPrefs = getActivity().getSharedPreferences("HabitSharedPref", Context.MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(habitCards); // myObject - instance of MyObject
        prefsEditor.putString("HabitCardAL", json);
        prefsEditor.apply();
    }

    public void refreshLineChart() {
        lineChart.invalidate();
    }

}
//We need day (date) & amount done in said day. We will figure out week & month total with layout later.