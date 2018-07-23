package com.example.hafiz.chartpracticev5.Activities.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.hafiz.chartpracticev5.Adapters.HabitAdapter;
import com.example.hafiz.chartpracticev5.Model.HabitCard;
import com.example.hafiz.chartpracticev5.R;
import com.github.mikephil.charting.charts.LineChart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainFragment extends Fragment {

    ListView habitListView;
    ArrayList<HabitCard> habitCards;
    HabitAdapter habitAdapter;
    private String input_text = "";
    private double input_double;
    SharedPreferences mPrefs;
    ChartFragment chartFragment;
    LineChart lineChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_activity, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        loadData();

        habitListView = (ListView) view.findViewById(R.id.habitListView);

        habitAdapter = new HabitAdapter(getActivity(), habitCards);//Do we do the entire application getapplicationcontext instead? Idk.
        habitListView.setAdapter(habitAdapter);

        habitListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getActivity());
                }
                builder.setTitle("Delete Habit?")
                        .setMessage("This will permanently delete the Habit.")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                habitCards.remove(i);
                                saveData();
                                habitAdapter.notifyDataSetChanged();



                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();

                return true;
            }
        });

        habitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, final long l) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getActivity());
                }
                final EditText input = new EditText(getActivity());

                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);
                builder.setTitle("Add Time")
                        .setMessage("Enter minutes put in")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                input_double = Double.parseDouble(input.getText().toString());
                                habitCards.get(i).setHabitUserMinutesTotal(input_double);
                                saveData();
                                habitAdapter.notifyDataSetChanged();


                                //chartFragment.refreshLineChart();
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        //.setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });




        com.getbase.floatingactionbutton.FloatingActionButton actionA = (com.getbase.floatingactionbutton.FloatingActionButton) view.findViewById(R.id.actionA);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getActivity());
                }
                final EditText input = new EditText(getActivity());

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setTitle("Add Habit")
                        .setMessage("Enter Habit Name - Study, Workout, etc.")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                input_text = input.getText().toString();

                                HabitCard habitCardHolder = new HabitCard(input_text, 0);
                                habitCards.add(habitCardHolder);

                                saveData();
                                habitAdapter.notifyDataSetChanged();

                            }

                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        //.setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getActivity().getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        return view;
    }

    private void saveData(){
        mPrefs = this.getActivity().getSharedPreferences("HabitSharedPref", Context.MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(habitCards); // myObject - instance of MyObject
        prefsEditor.putString("HabitCardAL", json);
        prefsEditor.apply();
    }

    private void loadData(){
        mPrefs = this.getActivity().getSharedPreferences("HabitSharedPref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("HabitCardAL", null);
        Type type = new TypeToken<ArrayList<HabitCard>>(){}.getType();
        habitCards = gson.fromJson(json, type);


        if (habitCards == null){
            habitCards = new ArrayList<>();
        }
    }


}
