package com.example.sqlite1;

public class TenSV {
    private int id;
    private  String tenSV;

    public TenSV(int id, String tenSV) {
        this.id = id;
        this.tenSV = tenSV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }
}
