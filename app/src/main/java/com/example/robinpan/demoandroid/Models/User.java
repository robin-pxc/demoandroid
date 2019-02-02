package com.example.robinpan.demoandroid.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String userName;
    private String firstName;
    private String lastName;

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel userParcel, int i) {
        userParcel.writeString(this.userName);
        userParcel.writeString(this.firstName);
        userParcel.writeString(this.lastName);
    }

    private User(Parcel in) {
        this.userName = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel userParcel) {
            return new User(userParcel);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };



}
