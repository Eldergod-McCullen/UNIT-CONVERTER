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

public class SpeedActivity extends AppCompatActivity {
    EditText input;
    Spinner selector;
    TextView MS, KH, KNO, LVV, EV, MACH, MH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_speed);

        input = findViewById(R.id.input);
        selector = findViewById(R.id.unit_selector);
        MS = findViewById(R.id.ms);
        KH = findViewById(R.id.kh);
        KNO = findViewById(R.id.kn);
        LVV = findViewById(R.id.lvv);
        EV = findViewById(R.id.ev);
        MACH = findViewById(R.id.mach);
        MH = findViewById(R.id.miho);

        String[] arr = {"m/s", "km/h", "kn", "Light velocity", "EV", "mach", "mph"};
        selector.setAdapter(new ArrayAdapter<>(SpeedActivity.this, android.R.layout.simple_expandable_list_item_1, arr));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
    }

    private void update() {
        if (!input.getText().toString().equals("") && !selector.getSelectedItem().toString().equals("")) {
            double in = Double.parseDouble(input.getText().toString());  //in IS THE INPUT PLACED ON THE TEXTBOX
            switch (selector.getSelectedItem().toString()) {   // THE VALUES BEING USE HERE ARE IN KILOMETRES.SIMPLE CONVERSIONS ARE DONE.
                case "m/s":                  // CASE POINT WHEN THE SELECTED VALUE IS IN KILOMETRES-BY DEFAULT
                    setms(in);
                    break;

                case "km/h":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setms(in / 3.6);       // 1KM = 1000m
                    break;

                case "kn":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setms(in / 1.9438);       // 1KM = 1000m
                    break;

                case "Light velocity":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    double lvel = 3.335641 * 0.000000001;
                    setms(in / lvel);       // 1KM = 1000m
                    break;

                case "EV":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setms(in / 0.0000335965);       // 1KM = 1000m
                    break;

                case "mach":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setms(in / 0.0033892974);       // 1KM = 1000m
                    break;

                case "mph":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setms(in / 2.2369);       // 1KM = 1000m
                    break;
            }
        }
    }

    // THIS METHOD IS USED FOR AUTOMATICALLY SETTING EVERY UNIT TO THEIR DESIGNATED VALUE BASED ON MANIPULATION IN KILOMETRES
    private void setms(double ms_in) {
        MS.setText(String.valueOf(ms_in));
        KH.setText(String.valueOf(ms_in * 3.6));
        KNO.setText(String.valueOf(ms_in * 1.9438));
        LVV.setText(String.valueOf(ms_in * 3.335641 * 0.000000001));
        EV.setText(String.valueOf(ms_in * 0.0000335965));
        MACH.setText(String.valueOf(ms_in * 0.0033892974));
        MH.setText(String.valueOf(ms_in * 2.2369));
    }
}