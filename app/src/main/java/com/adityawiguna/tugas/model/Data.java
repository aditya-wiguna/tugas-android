package com.adityawiguna.tugas.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    private String username;
    private String password;

    public Data(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected Data(Parcel in) {
        username = in.readString();
        password = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
    }
}
