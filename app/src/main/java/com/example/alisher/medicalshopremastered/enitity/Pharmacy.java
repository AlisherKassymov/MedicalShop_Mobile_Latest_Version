package com.example.alisher.medicalshopremastered.enitity;

public class Pharmacy {
    private int PharmId;

    private String PharmName;

    private String PharmAddress;

    private String PharmPhone;

    private String Time_at;

    public Pharmacy(int pharmId, String pharmName, String pharmAddress, String pharmPhone, String time_at) {
        PharmId = pharmId;
        PharmName = pharmName;
        PharmAddress = pharmAddress;
        PharmPhone = pharmPhone;
        Time_at = time_at;
    }

    public Pharmacy() {
    }

    public int getPharmId() {
        return PharmId;
    }

    public void setPharmId(int pharmId) {
        PharmId = pharmId;
    }

    public String getPharmName() {
        return PharmName;
    }

    public void setPharmName(String pharmName) {
        PharmName = pharmName;
    }

    public String getPharmAddress() {
        return PharmAddress;
    }

    public void setPharmAddress(String pharmAddress) {
        PharmAddress = pharmAddress;
    }

    public String getPharmPhone() {
        return PharmPhone;
    }

    public void setPharmPhone(String pharmPhone) {
        PharmPhone = pharmPhone;
    }

    public String getTime_at() {
        return Time_at;
    }

    public void setTime_at(String time_at) {
        Time_at = time_at;
    }
}
