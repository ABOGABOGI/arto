package com.example.cyber_net.apps.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Kereta implements Parcelable {
    String nama;

    public Kereta(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
    }

    protected Kereta(Parcel in) {
        this.nama = in.readString();
    }

    public static final Parcelable.Creator<Kereta> CREATOR = new Parcelable.Creator<Kereta>() {
        @Override
        public Kereta createFromParcel(Parcel source) {
            return new Kereta(source);
        }

        @Override
        public Kereta[] newArray(int size) {
            return new Kereta[size];
        }
    };
}
