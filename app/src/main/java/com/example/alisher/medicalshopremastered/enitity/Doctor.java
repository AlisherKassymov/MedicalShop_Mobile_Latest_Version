package com.example.alisher.medicalshopremastered.enitity;

public class Doctor {
    private int DoctorID;

    private String DoctorName;
    private String DoctorSurname;
    private String DoctorDescription;
    private Specialization doctorType;
    private String DoctorPhone;
    private Medical_Enterprise DoctorEnterprise;
    private String Price;

    public Doctor(int doctorID, String doctorName, String doctorSurname, String doctorDescription, Specialization doctorType, String doctorPhone, Medical_Enterprise doctorEnterprise, String price) {
        DoctorID = doctorID;
        DoctorName = doctorName;
        DoctorSurname = doctorSurname;
        DoctorDescription = doctorDescription;
        this.doctorType = doctorType;
        DoctorPhone = doctorPhone;
        DoctorEnterprise = doctorEnterprise;
        Price = price;
    }

    public Doctor() {
    }

    public int getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(int doctorID) {
        DoctorID = doctorID;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getDoctorSurname() {
        return DoctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        DoctorSurname = doctorSurname;
    }

    public String getDoctorDescription() {
        return DoctorDescription;
    }

    public void setDoctorDescription(String doctorDescription) {
        DoctorDescription = doctorDescription;
    }

    public Specialization getDoctorType() {
        return doctorType;
    }

    public String getDoctorPhone() {
        return DoctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        DoctorPhone = doctorPhone;
    }

    public Medical_Enterprise getDoctorEnterprise() {
        return DoctorEnterprise;
    }

    public void setDoctorEnterprise(Medical_Enterprise doctorEnterprise) {
        DoctorEnterprise = doctorEnterprise;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setDoctorType(Specialization doctorType) {
        this.doctorType = doctorType;
    }


}
