package com.example.cyber_net.apps.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fajar on 4/18/2019.
 */

public class Kategori_pulsa {
    public List<Datum> data = new ArrayList<>();
    public class Datum {
        @SerializedName("id")
        private Integer id;
        @SerializedName("nama")
        private String nama;
        @SerializedName("jenis")
        private String jenis;

        public String getNama() {
            return nama;
        }

        public Integer getId() {
            return id;
        }

        public String getJenis() {
            return jenis;
        }
    }
}
