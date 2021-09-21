package com.sadikahmetozdemir.caseforzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityList extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        CircleImageView listImageView;
        TextView textViewName;
        TextView textViewPremium;
        TextView textViewRandevuAl;


        listImageView=findViewById(R.id.listImageView);
        textViewName=findViewById(R.id.textViewName);
        textViewPremium=findViewById(R.id.textViewPremium);
        textViewRandevuAl=findViewById(R.id.textViewRandevuAl);



        Intent intent=getIntent();
        String imageUrl=intent.getStringExtra("doctorimage");
        Glide.with(this)
                .load(imageUrl)
                .into(listImageView);
        String doctorName=intent.getStringExtra("doctorname");
        textViewName.setText(doctorName);
        String userStatus=intent.getStringExtra("status");


        textViewRandevuAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent= new Intent(ActivityList.this,ActivityMeeting.class);
                startActivity(mIntent);

            }
        });

















    }
}