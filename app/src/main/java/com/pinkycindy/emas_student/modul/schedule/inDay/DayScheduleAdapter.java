package com.pinkycindy.emas_student.modul.schedule.inDay;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pinkycindy.emas_student.R;
import com.pinkycindy.emas_student.data.model.Classroom;

import java.util.ArrayList;

/**
 * Created by Pinky Cindy
 */
public class DayScheduleAdapter extends RecyclerView.Adapter<DayScheduleAdapter.DayScheduleViewHolder> {

    private ArrayList<Classroom> classroomItem;
    Context context;

    public DayScheduleAdapter(ArrayList<Classroom> classroomItem, Context context) {
        this.classroomItem = classroomItem;
        this.context = context;
    }

    @NonNull
    @Override
    public DayScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new DayScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DayScheduleViewHolder holder, int position) {

       // Log.d("class", String.valueOf(classroomItem.get(position).getId()));

        String[] strDays = new String[] {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
                "Friday" };
        // Day_OF_WEEK starts from 1 while array index starts from 0
        String day = strDays[classroomItem.get(position).getDayFirst()];
        String day2 = strDays[classroomItem.get(position).getDaySecond()];

        holder.tvClassName.setText(classroomItem.get(position).getName());
        holder.tvSpotName.setText(classroomItem.get(position).getNameSpots());
        holder.tvAdress.setText(classroomItem.get(position).getAddress());
        holder.tvDay.setText(day + " : " + classroomItem.get(position).getHourFirst());
        holder.tvDay2.setText(day2 + " : "+classroomItem.get(position).getHourSecond());
    }

    @Override
    public int getItemCount() {
        return classroomItem.size();
    }

//    public ClassroomItem getItem(){
//
//    }

    public class DayScheduleViewHolder extends RecyclerView.ViewHolder{
        private TextView tvClassName, tvSpotName, tvAdress, tvHour, tvDay, tvDay2;
        public DayScheduleViewHolder(View itemView) {
            super(itemView);
            tvClassName = itemView.findViewById(R.id.tv_class_name);
            tvSpotName = itemView.findViewById(R.id.tv_spot_name);
            tvAdress = itemView.findViewById(R.id.tv_address);
            tvDay = itemView.findViewById(R.id.tv_day);
            tvDay2 = itemView.findViewById(R.id.tv_day2);

        }
    }
}
