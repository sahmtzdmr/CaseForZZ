package com.sadikahmetozdemir.caseforzz;

import android.media.Image;
import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doctor  {

    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("user_status")
    @Expose
    private String userStatus;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("image")
    @Expose
    private DoctorsImage doctorsImage;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public DoctorsImage getDoctorsImage() {
        return doctorsImage;
    }

    public void setDoctorsImage(DoctorsImage doctorsImage) {
        this.doctorsImage = doctorsImage;
    }
}
