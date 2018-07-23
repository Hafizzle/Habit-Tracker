package com.example.hafiz.chartpracticev5.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hafiz.chartpracticev5.Model.HabitCard;
import com.example.hafiz.chartpracticev5.R;

import java.util.ArrayList;

/**
 * Created by hafiz on 7/7/2018.
 */

public class HabitAdapter extends ArrayAdapter<HabitCard> {

    private final Activity mContext;
    private final ArrayList<HabitCard> habitCards;

    public class ViewHolder
    {
        TextView habitName;
    }


    public HabitAdapter(@NonNull Activity context, ArrayList<HabitCard> habitCards) {
        super(context, R.layout.cardview_custom_row, R.id.habitTV, habitCards);
        this.mContext = context;
        this.habitCards = habitCards;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView == null)
        {
            LayoutInflater layoutInflater = mContext.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.cardview_custom_row, parent, false);

            holder = new ViewHolder();
            holder.habitName = (TextView)convertView.findViewById(R.id.habitTV);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.habitName.setText(habitCards.get(position).getHabitName());

        return convertView;
    }
}
