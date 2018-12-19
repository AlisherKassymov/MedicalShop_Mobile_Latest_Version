package com.example.alisher.medicalshopremastered.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.alisher.medicalshopremastered.R;

public class MedicineItemActivity extends AppCompatActivity {
    private static final String NAME="name";
    private static final String DESC="description";
    private static final String CONTRADICTION="contradiction";
    private static final String USEINCASE="useInCase";
    private static final String PRICE="price";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_item);


        Intent intent=getIntent();
        String name=intent.getStringExtra(NAME);
        String desc=intent.getStringExtra(DESC);
        String contr=intent.getStringExtra(CONTRADICTION);
        String usage=intent.getStringExtra(USEINCASE);
        String price=intent.getStringExtra(PRICE);

        TextView textView1=findViewById(R.id.medicineNameClick);
        TextView textView2=findViewById(R.id.medicinePriceClick);
        TextView textView3=findViewById(R.id.medicineDescriptionClick);
        TextView textView4=findViewById(R.id.contradictionClick);
        TextView textView5=findViewById(R.id.use_in_case_Click);

        textView1.setText(name);
        textView2.setText(price);
        textView3.setText(desc);
        textView4.setText(contr);
        textView5.setText(usage);
    }
}
