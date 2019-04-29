package com.example.cyber_net.apps.model;

public class Status {
    String tgl;
    String transaksi;
    String status;

    public Status(String tgl, String transaksi, String status) {
        this.tgl = tgl;
        this.transaksi = transaksi;
        this.status = status;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(String transaksi) {
        this.transaksi = transaksi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
