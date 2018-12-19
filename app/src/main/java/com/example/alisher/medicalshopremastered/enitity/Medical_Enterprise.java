package com.example.alisher.medicalshopremastered.enitity;

public class Medical_Enterprise {
    private int MedID;

    private String MedName;
    private String MedDescription;
    private String MedAddress;
    private String Time_at;

    public Medical_Enterprise(int medID, String medName, String medDescription, String medAddress, String time_at) {
        MedID = medID;
        MedName = medName;
        MedDescription = medDescription;
        MedAddress = medAddress;
        Time_at = time_at;
    }

    public Medical_Enterprise() {
    }

    public int getMedID() {
        return MedID;
    }

    public void setMedID(int medID) {
        MedID = medID;
    }

    public String getMedName() {
        return MedName;
    }

    public void setMedName(String medName) {
        MedName = medName;
    }

    public String getMedDescription() {
        return MedDescription;
    }

    public void setMedDescription(String medDescription) {
        MedDescription = medDescription;
    }

    public String getMedAddress() {
        return MedAddress;
    }

    public void setMedAddress(String medAddress) {
        MedAddress = medAddress;
    }

    public String getTime_at() {
        return Time_at;
    }

    public void setTime_at(String time_at) {
        Time_at = time_at;
    }
}
