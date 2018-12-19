package com.example.alisher.medicalshopremastered.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.alisher.medicalshopremastered.R;

public class PharmacyItemActivity extends AppCompatActivity {

    private static final String NAME="Pharmname";
    private static final String ADDRESS="Pharmaddress";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_item);

        Intent intent=getIntent();
        String name=intent.getStringExtra(NAME);
        String address=intent.getStringExtra(ADDRESS);

        TextView textView1=findViewById(R.id.pharmacyNameClick);
        TextView textView2=findViewById(R.id.pharmacyAddressClick);

        textView1.setText(name);
        textView2.setText(address);
    }
}
