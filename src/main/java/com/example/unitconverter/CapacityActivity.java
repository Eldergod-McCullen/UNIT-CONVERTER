package com.example.unitconverter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CapacityActivity extends AppCompatActivity {
    EditText input;
    Spinner selector;
    TextView METRE,CCEN,CMILL,LIT,MIL,GAL,PINT,FLUIDO,DRAM,CCC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_capacity);

        input = findViewById(R.id.input);
        selector = findViewById(R.id.unit_selector);
        METRE = findViewById(R.id.cmet);
        CCEN = findViewById(R.id.ccen);
        CMILL = findViewById(R.id.cmil);
        LIT = findViewById(R.id.lit);
        MIL = findViewById(R.id.ml);
        GAL = findViewById(R.id.gal);
        PINT = findViewById(R.id.pint);
        FLUIDO = findViewById(R.id.fluidounce);
        DRAM = findViewById(R.id.dram);
        CCC = findViewById(R.id.cc);

        String[] arr = {"m3","cm3","mm3","ml","l","fl oz","gal","CC","pt","dr"};
        selector.setAdapter(new ArrayAdapter<>(CapacityActivity.this, android.R.layout.simple_expandable_list_item_1, arr));

        selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                update();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void update() {
        if (!input.getText().toString().equals("") && !selector.getSelectedItem().toString().equals("")) {
            double in = Double.parseDouble(input.getText().toString());  //in IS THE INPUT PLACED ON THE TEXTBOX
            switch (selector.getSelectedItem().toString()) {   // THE VALUES BEING USE HERE ARE IN KILOMETRES.SIMPLE CONVERSIONS ARE DONE.
                case "m3":                 // CASE POINT WHEN THE SELECTED VALUE IS IN KILOMETRES-BY DEFAULT
                    setm3(in);
                    break;

                case "cm3":                  // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setm3(in / 1000000);        // 1m3 = 1000000cm3
                    break;

                case "mm3":                           // CASE POINT WHEN THE SELECTED VALUE IS IN CENTIMETRES
                    setm3(in / 1000000000);             // 1m3 = 1000000000mm3
                    break;

                case "ml":                           // CASE POINT WHEN THE SELECTED VALUE IS IN CENTIMETRES
                    setm3(in / 1000000);             // 1m3 = 1000000ml
                    break;

                case "l":                           // CASE POINT WHEN THE SELECTED VALUE IS IN CENTIMETRES
                    setm3(in / 1000);             // 1m3 = 1000l
                    break;

                case "fl oz":                           // CASE POINT WHEN THE SELECTED VALUE IS IN CENTIMETRES
                    setm3(in / 35195.08);                 // 1m3 = 35195.08fl oz
                    break;

                case "gal":                           // CASE POINT WHEN THE SELECTED VALUE IS IN CENTIMETRES
                    setm3(in / 219.97);                 // 1m3 = 219.96gal
                    break;

                case "CC":                           // CASE POINT WHEN THE SELECTED VALUE IS IN CENTIMETRES
                    setm3(in / 1000000);                 // 1m3 = 1000000cc
                    break;

                case "pt":                           // CASE POINT WHEN THE SELECTED VALUE IS IN CENTIMETRES
                    setm3(in / 1759.75);                 // 1m3 = 1759.75pt
                    break;

                case "dr":                             // CASE POINT WHEN THE SELECTED VALUE IS IN CENTIMETRES
                    setm3(in / 270512.18);                 // 1m3 = 270512.18dr
                    break;
            }
        }
    }

    // THIS METHOD IS USED FOR AUTOMATICALLY SETTING EVERY UNIT TO THEIR DESIGNATED VALUE BASED ON MANIPULATION IN KILOMETRES
    private void setm3(double m3_in) {
        METRE.setText(String.valueOf(m3_in));
        CCEN.setText(String.valueOf(m3_in * 1000000));
        CMILL.setText(String.valueOf(m3_in * 1000000000));
        LIT.setText(String.valueOf(m3_in * 1000));
        MIL.setText(String.valueOf(m3_in * 1000000));
        GAL.setText(String.valueOf(m3_in * 219.97));
        PINT.setText(String.valueOf(m3_in * 1759.75));
        FLUIDO.setText(String.valueOf(m3_in * 35195.08));
        DRAM.setText(String.valueOf(m3_in * 270512.18));
        CCC.setText(String.valueOf(m3_in * 1000000));

    }
}