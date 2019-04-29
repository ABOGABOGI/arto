package com.example.cyber_net.apps.model;

/**
 * Created by muhyi on 4/24/2019.
 */

public class Topup {
    String id;
    Integer cms_users_id;
    Integer nominal;
    String idva;
    String status;
    String uniqid;
    String tanggal;
    Integer api_status;

    public Integer getApi_status() {
        return api_status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public Integer getCms_users_id() {
        return cms_users_id;
    }

    public String getId() {
        return id;
    }

    public Integer getNominal() {
        return nominal;
    }

    public String getIdva() {
        return idva;
    }

    public String getStatus() {
        return status;
    }

    public String getUniqid() {
        return uniqid;
    }
}
