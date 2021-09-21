package com.sadikahmetozdemir.caseforzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityListWithoutPremium extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_without_premium);

        CircleImageView circleImageView;
        TextView textViewWithoutPremium;
        TextView textViewPremium;
        textViewPremium=findViewById(R.id.textViewPremium);
        circleImageView=findViewById(R.id.listImageViewWithoutPremium);
        textViewWithoutPremium=findViewById(R.id.textViewNameWithoutPremium);
        Intent intent=getIntent();
        String imageUrl=intent.getStringExtra("doctorimage");
        Glide.with(this)
                .load(imageUrl)
                .into(circleImageView);
        String doctorName=intent.getStringExtra("doctorname");
        textViewWithoutPremium.setText(doctorName);
        textViewPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(ActivityListWithoutPremium.this,ActivityPay.class);
                startActivity(mIntent);
            }
        });


    }
}