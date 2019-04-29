package com.example.cyber_net.apps.model;

public class News {
    String judul;
    String url;
    String isi;

    public News(String judul, String url, String isi) {
        this.judul = judul;
        this.url = url;
        this.isi = isi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
