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

public class PressureActivity extends AppCompatActivity {
    EditText input;
    Spinner selector;
    TextView NSM2, PASCAL, KILOPASC, BAR, PSI, KSI, STANDA, MILLM, MILLB, ATT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pressure);

        input = findViewById(R.id.input);
        selector = findViewById(R.id.unit_selector);
        NSM2 = findViewById(R.id.nsm2);
        PASCAL = findViewById(R.id.pascal);
        BAR = findViewById(R.id.bar);
        PSI = findViewById(R.id.psi);
        KSI = findViewById(R.id.ksi);
        STANDA = findViewById(R.id.sta);
        MILLM = findViewById(R.id.milm);
        MILLB = findViewById(R.id.milb);
        ATT = findViewById(R.id.att);
        KILOPASC = findViewById(R.id.kp);

        String[] arr = {"N/m2", "Pa", "kPa", "bar", "psi", "ksi", "atm", "mmHg", "mbar", "at"};
        selector.setAdapter(new ArrayAdapter<>(PressureActivity.this, android.R.layout.simple_expandable_list_item_1, arr));

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
                case "Pa":                 // CASE POINT WHEN THE SELECTED VALUE IS IN KILOMETRES-BY DEFAULT
                    setPascal(in);
                    break;

                case "N/m2":                  // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setPascal(in / 1);              // 1KM = 1000m
                    break;

                case "kPa":                  // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setPascal(in / 0.001);              // 1KM = 1000m
                    break;

                case "bar":                  // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setPascal(in / 0.00001);              // 1KM = 1000m
                    break;

                case "psi":                  // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setPascal(in / 0.0001450377);              // 1KM = 1000m
                    break;

                case "ksi":                        // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setPascal(in / 14504000);              // 1KM = 1000m
                    break;

                case "atm":                        // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setPascal(in / 0.0000098692);              // 1KM = 1000m
                    break;

                case "mmHg":                        // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setPascal(in / 0.0075006376);              // 1KM = 1000m
                    break;

                case "mbar":                        // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setPascal(in / 0.01);              // 1KM = 1000m
                    break;

                case "at":                        // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setPascal(in / 0.0000101972);              // 1KM = 1000m
                    break;
            }
        }
    }

    // THIS METHOD IS USED FOR AUTOMATICALLY SETTING EVERY UNIT TO THEIR DESIGNATED VALUE BASED ON MANIPULATION IN KILOMETRES
    private void setPascal(double Pascal_in) {
        PASCAL.setText(String.valueOf(Pascal_in));
        NSM2.setText(String.valueOf(Pascal_in * 1));
        KILOPASC.setText(String.valueOf(Pascal_in * 0.001));
        BAR.setText(String.valueOf(Pascal_in * 0.00001));
        PSI.setText(String.valueOf(Pascal_in * 0.0001450377));
        KSI.setText(String.valueOf(Pascal_in * 14504000));
        STANDA.setText(String.valueOf(Pascal_in * 0.0000098692));
        MILLM.setText(String.valueOf(Pascal_in * 0.0075006376));
        MILLB.setText(String.valueOf(Pascal_in * 0.01));
        ATT.setText(String.valueOf(Pascal_in * 0.0000101972));
    }
}