package com.example.alisher.medicalshopremastered.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import com.example.alisher.medicalshopremastered.R;
import android.os.Bundle;
import android.widget.TextView;

public class DoctorItemActivity extends AppCompatActivity {
    private static final String NAME="name";
    private static final String DESC="description";
    private static final String PHONE="phone";
    private static final String SURNAME="surname";
    private static final String PRICE="price";
    private static final String TYPE="specialization";
    private static final String ENTERPRISE="enterprise";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_item);

        Intent intent=getIntent();
        String name=intent.getStringExtra(NAME);
        String description=intent.getStringExtra(DESC);
        String phone=intent.getStringExtra(PHONE);
        String surname=intent.getStringExtra(SURNAME);
        String price=intent.getStringExtra(PRICE);
        String specialization=intent.getStringExtra(TYPE);
        String enterprise=intent.getStringExtra(ENTERPRISE);


        TextView textView1=findViewById(R.id.doctorNameClick);
        TextView textView2=findViewById(R.id.doctorDescriptionClick);
        TextView textView3=findViewById(R.id.doctorPhoneClick);
        TextView textView4=findViewById(R.id.doctorSurnameClick);
        TextView textView5=findViewById(R.id.doctorTypeClick);
        TextView textView6=findViewById(R.id.doctorEnterpriseClick);
        TextView textView7=findViewById(R.id.doctorPriceClick);

        textView1.setText(name);
        textView2.setText(description);
        textView3.setText(phone);
        textView4.setText(surname);
        textView5.setText(specialization);
        textView6.setText(enterprise);
        textView7.setText(price);

    }
}
