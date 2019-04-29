package com.example.cyber_net.apps.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fajar on 4/18/2019.
 */

public class Pulsa {
    public List<Datum> data = new ArrayList<>();
    public class Datum {
        @SerializedName("id")
        private Integer id;
        @SerializedName("kode_pulsa")
        private String kode_pulsa;
        @SerializedName("keterangan")
        private String keterangan;
        @SerializedName("harga_jual")
        private Integer harga_jual;

        public Integer getId() {
            return id;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public Integer getHarga_jual() {
            return harga_jual;
        }

        public String getKode_pulsa() {
            return kode_pulsa;
        }
    }
}
