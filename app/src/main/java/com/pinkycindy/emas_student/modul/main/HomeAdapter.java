package com.pinkycindy.emas_student.modul.main;

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
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private ArrayList<Classroom> classroomItem;
    Context context;

    public HomeAdapter(ArrayList<Classroom> classroomItem, Context context) {
        this.classroomItem = classroomItem;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule_today, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {

       // Log.d("class", String.valueOf(classroomItem.get(position).getId()));

        holder.tvClassName.setText(classroomItem.get(position).getName());
        holder.tvSpotName.setText(classroomItem.get(position).getNameSpots());
        holder.tvAdress.setText(classroomItem.get(position).getAddress());
        holder.tvHour.setText(classroomItem.get(position).getHourFirst());
    }

    @Override
    public int getItemCount() {
        return classroomItem.size();
    }

//    public ClassroomItem getItem(){
//
//    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{
        private TextView tvClassName, tvSpotName, tvAdress, tvHour;
        public HomeViewHolder(View itemView) {
            super(itemView);
            tvClassName = itemView.findViewById(R.id.tv_class_name);
            tvSpotName = itemView.findViewById(R.id.tv_spot_name);
            tvAdress = itemView.findViewById(R.id.tv_address);
            tvHour = itemView.findViewById(R.id.tv_hour);

        }
    }
}
