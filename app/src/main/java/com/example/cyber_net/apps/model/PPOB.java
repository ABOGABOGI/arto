package com.example.cyber_net.apps.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fajar on 4/16/2019.
 */

public class PPOB {
    public List<Datum> data = new ArrayList<>();
    public class Datum {
        @SerializedName("id")
        private String id;
        @SerializedName("kode_ppob")
        private String kode_ppob;
        @SerializedName("keterangan")
        private String keterangan;
        @SerializedName("harga_admin")
        private String harga_admin;
        @SerializedName("harga_mitra")
        private String harga_mitra;

        public String getId() {
            return id;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public String getHarga_admin() {
            return harga_admin;
        }

        public String getHarga_mitra() {
            return harga_mitra;
        }

        public String getKode_ppob() {
            return kode_ppob;
        }
    }
}
