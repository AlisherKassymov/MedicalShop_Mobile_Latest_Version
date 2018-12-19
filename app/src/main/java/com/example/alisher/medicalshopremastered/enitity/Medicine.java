package com.example.alisher.medicalshopremastered.enitity;

public class Medicine {
    private int MedicineID;

    private String Name;

    private String Description;

    private int Quantity;

    private String MedicineCode ;

    private String Img;

    private String Use_in_case;



    private String Contradiction;

    private String Price;

    public Medicine(int medicineID, String name, String description, int quantity, String medicineCode, String img, String use_in_case, String contradiction, String price) {
        MedicineID = medicineID;
        Name = name;
        Description = description;
        Quantity = quantity;
        MedicineCode = medicineCode;
        Img = img;
        Use_in_case = use_in_case;
        Contradiction = contradiction;
        Price = price;
    }

    public Medicine() {

    }

    public Medicine(String name, String price) {
        Name = name;
        Price = price;
    }

    public Medicine(String name, String description, String price) {
        Name = name;
        Description = description;
        Price = price;
    }

    public int getMedicineID() {
        return MedicineID;
    }

    public void setMedicineID(int medicineID) {
        MedicineID = medicineID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getMedicineCode() {
        return MedicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        MedicineCode = medicineCode;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getUse_in_case() {
        return Use_in_case;
    }

    public void setUse_in_case(String use_in_case) {
        Use_in_case = use_in_case;
    }

    public String getContradiction() {
        return Contradiction;
    }

    public void setContradiction(String contradiction) {
        Contradiction = contradiction;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
