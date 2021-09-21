package com.sadikahmetozdemir.caseforzz;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Gson gson = new GsonBuilder().setLenient().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.mobillium.com/android/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    Doctor doctor = new Doctor();
    ArrayList<Doctor> doctorArrayList = new ArrayList<>();
    ArrayList<Doctor> filterGenderList = new ArrayList<>();
    ArrayList<Doctor> filteredList = new ArrayList<>();
    Boolean aBoolean = false;
    Boolean check = false;

    DoctorAdapter doctorAdapter;


    RecyclerView recyclerView;
    EditText searchView;
    CheckBox checkBoxWoman, checkBoxMan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView = findViewById(R.id.searchView);
        checkBoxWoman = findViewById(R.id.checkBoxWoman);
        checkBoxMan = findViewById(R.id.checkBoxMan);
        checkBoxMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxWoman.setChecked(false);
                if (!check) {

                    filterSex("male");

                    check = true;




                }

                else    {

                    doctorAdapter.filterList(doctorArrayList);
                    check=false;
                    filterGenderList.clear();

                }



            }

        });





        checkBoxWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxMan.setChecked(false);

                if (!check) {


                    filterSex("female");

                    check = true;



                }
                else {
                    filterGenderList.clear();


                    doctorAdapter.filterList(doctorArrayList);
                    check=false;


                }

            }
        });




        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterDoctors(s.toString());
                aBoolean = true;


            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        GetDoctor();
    }

    private void filterSex(String text) {

        for (int i = 0; i < doctorArrayList.size() - 1; i++) {
            if (doctorArrayList.get(i).getGender().equals(text)) {
                filterGenderList.add(doctorArrayList.get(i));
            }
        }

        doctorAdapter.filterList(filterGenderList);
    }


    private void filterDoctors(String text) {
        filteredList = new ArrayList<>();
        for (Doctor item : doctorArrayList) {
            if (item.getFullName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        doctorAdapter.filterList(filteredList);
    }


    public void initDoctors(ArrayList<Doctor> doctors) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        doctorAdapter = new DoctorAdapter(doctors, getApplicationContext());

        recyclerView.setAdapter(doctorAdapter);


    }

    public void GetDoctor() {

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<Example> call = requestInterface.getDoctorsJson();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {


                doctorArrayList = (ArrayList<Doctor>) response.body().getDoctors();
                initDoctors(doctorArrayList);


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }


}
