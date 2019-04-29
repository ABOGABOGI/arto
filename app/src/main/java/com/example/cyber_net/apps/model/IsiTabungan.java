package com.example.cyber_net.apps.model;

import android.os.Parcel;
import android.os.Parcelable;

public class IsiTabungan implements Parcelable {
    String produk;
    int image;

    public IsiTabungan(String produk, int image) {
        this.produk = produk;
        this.image = image;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.produk);
        dest.writeInt(this.image);
    }

    protected IsiTabungan(Parcel in) {
        this.produk = in.readString();
        this.image = in.readInt();
    }

    public static final Parcelable.Creator<IsiTabungan> CREATOR = new Parcelable.Creator<IsiTabungan>() {
        @Override
        public IsiTabungan createFromParcel(Parcel source) {
            return new IsiTabungan(source);
        }

        @Override
        public IsiTabungan[] newArray(int size) {
            return new IsiTabungan[size];
        }
    };
}
