package com.example.cyber_net.apps.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fajar on 4/17/2019.
 */

public class Inquiry {
    String api_message;
    String nama;
    String kode_produk;
    String reff_id;
    String jumlah;
    String customer;
    String nomor;

    public String getNomor() {
        return nomor;
    }

    public String getApi_message() {
        return api_message;
    }

    public String getNama() {
        return nama;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getCustomer() {
        return customer;
    }

    public String getKode_produk() {
        return kode_produk;
    }

    public String getReff_id() {
        return reff_id;
    }
}
