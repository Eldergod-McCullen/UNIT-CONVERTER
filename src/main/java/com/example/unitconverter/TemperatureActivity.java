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

public class TemperatureActivity extends AppCompatActivity {
    EditText input;
    Spinner selector;
    TextView DEG, KEL, FAHR, RANK, REA, TPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temperature);

        input = findViewById(R.id.input);
        selector = findViewById(R.id.unit_selector);
        DEG = findViewById(R.id.degrees);
        KEL = findViewById(R.id.kelvin);
        FAHR = findViewById(R.id.fahrenheit);
        RANK = findViewById(R.id.rankine);
        REA = findViewById(R.id.reaumur);
        TPW = findViewById(R.id.triplew);

        String[] arr = {"Degrees Celcius", "Kelvin", "Fahrenheit", "Rankine", "Reaumur", "Triple Point of Water"};
        selector.setAdapter(new ArrayAdapter<>(TemperatureActivity.this, android.R.layout.simple_expandable_list_item_1, arr));

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
                case "Degrees Celcius":                 // CASE POINT WHEN THE SELECTED VALUE IS IN KILOGRAMS-BY DEFAULT
                    setDeg(in);
                    break;

                case "Kelvin":                 // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setDeg(in / 274.15);        // 1Degree = 274.15Kelvin
                    break;

                case "Fahrenheit":                 // CASE POINT WHEN THE SELECTED VALUE IS IN MILLIGRAMS
                    setDeg(in / 33.8);                   // 1Degree = 33.8 Fahrenheit
                    break;

                case "Rankine":                 // CASE POINT WHEN THE SELECTED VALUE IS IN MICROGRAMS
                    setDeg(in / 493.47);    // 1Degree = 493.47Rankine
                    break;

                case "Reaumur":                             // CASE POINT WHEN THE SELECTED VALUE IS IN NANOGRAMS
                    // INTRODUCE THE VARIABLE nano DUE TO THE HIGH NUMBER OF ZEROS
                    setDeg(in / 0.8);                    // 1Degree = 0.8Reaumur
                    break;

                case "Triple Point of Water":                 // CASE POINT WHEN THE SELECTED VALUE IS IN POUNDS
                    setDeg(in / 1.004);             // 1Degree = 1.004 Triple Point of Water
                    break;
            }
        }
    }

    // THIS METHOD IS USED FOR AUTOMATICALLY SETTING EVERY UNIT TO THEIR DESIGNATED VALUE BASED ON MANIPULATION IN KILOMETRES
    private void setDeg(double Deg_in) {
        DEG.setText(String.valueOf(Deg_in));
        KEL.setText(String.valueOf(Deg_in * 274.15));
        FAHR.setText(String.valueOf(Deg_in * 33.8 ));
        RANK.setText(String.valueOf(Deg_in * 493.47));
        REA.setText(String.valueOf(Deg_in * 0.8));
        TPW.setText(String.valueOf(Deg_in * 1.004));
    }
}