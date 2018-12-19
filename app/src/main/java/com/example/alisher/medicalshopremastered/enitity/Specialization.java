package com.example.alisher.medicalshopremastered.enitity;

public class Specialization {
    private int SpecID;
    private String SpecName;
    private String SpecDescription;

    public Specialization(int specID, String specName, String specDescription) {
        SpecID = specID;
        SpecName = specName;
        SpecDescription = specDescription;
    }

    public Specialization() {
    }

    public int getSpecID() {
        return SpecID;
    }

    public void setSpecID(int specID) {
        SpecID = specID;
    }

    public String getSpecName() {
        return SpecName;
    }

    public void setSpecName(String specName) {
        SpecName = specName;
    }

    public String getSpecDescription() {
        return SpecDescription;
    }

    public void setSpecDescription(String specDescription) {
        SpecDescription = specDescription;
    }
}
