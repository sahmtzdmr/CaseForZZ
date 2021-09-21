package com.sadikahmetozdemir.caseforzz;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    private ArrayList<Doctor> doctors;

    private Context context;


    public DoctorAdapter(ArrayList<Doctor> doctors, Context context) {
        this.doctors = doctors;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);


        holder.textView.setText(doctors.get(position).getFullName());
        Glide.with(context).load(doctor.getDoctorsImage().getUrl()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = doctor.getUserStatus();
                if ((status.equals("premium"))) {


                    Intent intent = new Intent(context, ActivityList.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.putExtra("doctorimage", doctor.getDoctorsImage().getUrl());
                    intent.putExtra("doctorname", doctor.getFullName());
                    intent.putExtra("status", doctor.getUserStatus());
                    context.startActivity(intent);


                } else {
                    Intent intent = new Intent(context, ActivityListWithoutPremium.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.putExtra("doctorimage", doctor.getDoctorsImage().getUrl());
                    intent.putExtra("doctorname", doctor.getFullName());
                    intent.putExtra("status", doctor.getUserStatus());
                    context.startActivity(intent);


                }


            }
        });


    }


    @Override
    public int getItemCount() {


        return doctors.size();
    }


    public void filterList(ArrayList<Doctor> filteredList) {
        doctors = filteredList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imageView;
        public TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);


        }
    }
}
