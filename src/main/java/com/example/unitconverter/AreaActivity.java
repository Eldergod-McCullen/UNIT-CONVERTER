package com.example.unitconverter;

import android.annotation.SuppressLint;
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

public class AreaActivity extends AppCompatActivity {
    EditText input;
    Spinner selector;
    TextView SQMET, HEC, AC, SQMIL, ARE, BARN, SQROD, SQP, SQF, SQY, ROOD;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_area);

        input = findViewById(R.id.input);
        selector = findViewById(R.id.unit_selector);
        SQMET = findViewById(R.id.sqmet);
        HEC = findViewById(R.id.h);
        AC = findViewById(R.id.ac);
        SQMIL = findViewById(R.id.sqmil);
        ARE = findViewById(R.id.are);
        BARN = findViewById(R.id.barn);
        SQROD = findViewById(R.id.sqrod);
        SQP = findViewById(R.id.sqpole);
        SQF = findViewById(R.id.sqf);
        SQY = findViewById(R.id.sqy);
        ROOD = findViewById(R.id.rood);

        String[] arr = {"m2", "ha", "ac", "mile2", "are", "b", "rod2", "pole2", "ft2", "yd2", "rood"};
        selector.setAdapter(new ArrayAdapter<>(AreaActivity.this, android.R.layout.simple_expandable_list_item_1, arr));

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
                case "m2":                  // CASE POINT WHEN THE SELECTED VALUE IS IN KILOMETRES-BY DEFAULT
                    setm2(in);
                    break;

                case "ha":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setm2(in / 0.0001);       // 1KM = 1000m
                    break;

                case "ac":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setm2(in / 0.0002471);       // 1KM = 1000m
                    break;

                case "mile2":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    double mile = 3.861 * 0.0000001;
                    setm2(in / mile);         // 1KM = 1000m
                    break;

                case "are":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setm2(in / 0.01);       // 1KM = 1000m
                    break;

                case "b":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    int barn = 100000000 * 100000000 * 100000000 * 10000;
                    setm2(in / barn);       // 1KM = 1000m
                    break;

                case "rod2":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setm2(in / 0.039536861);       // 1KM = 1000m
                    break;

                case "pole2":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setm2(in / 0.039536861);       // 1KM = 1000m
                    break;

                case "ft2":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setm2(in / 10.764);       // 1KM = 1000m
                    break;

                case "yd2":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setm2(in / 1.196);       // 1KM = 1000m
                    break;

                case "rood":                   // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setm2(in / 0.0009884);       // 1KM = 1000m
                    break;
            }
        }
    }

    // THIS METHOD IS USED FOR AUTOMATICALLY SETTING EVERY UNIT TO THEIR DESIGNATED VALUE BASED ON MANIPULATION IN KILOMETRES
    private void setm2(double m2_in) {
        SQMET.setText(String.valueOf(m2_in));
        HEC.setText(String.valueOf(m2_in * 0.0001));
        AC.setText(String.valueOf(m2_in * 0.0002471));
        SQMIL.setText(String.valueOf(m2_in * 3.861 * 0.0000001));
        ARE.setText(String.valueOf(m2_in * 0.01));
        BARN.setText(String.valueOf(m2_in * 100000000 * 100000000 * 100000000 * 10000));
        SQROD.setText(String.valueOf(m2_in * 0.039536861));
        SQP.setText(String.valueOf(m2_in * 0.039536861));
        SQF.setText(String.valueOf(m2_in * 10.764));
        SQY.setText(String.valueOf(m2_in * 1.196));
        ROOD.setText(String.valueOf(m2_in * 0.0009884));
    }
}