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

public class LengthActivity extends AppCompatActivity {
    EditText input;
    Spinner selector;
    TextView KM,M,CM,MM,MicM,NM,MILE,YARD,FEET,INCHES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_length);

        input = findViewById(R.id.input);
        selector = findViewById(R.id.unit_selector);
        KM = findViewById(R.id.km);
        M = findViewById(R.id.met);
        CM = findViewById(R.id.cm);
        MM = findViewById(R.id.mm);
        MicM = findViewById(R.id.micm);
        NM = findViewById(R.id.nm);
        MILE = findViewById(R.id.miles);
        YARD = findViewById(R.id.yards);
        FEET = findViewById(R.id.feet);
        INCHES = findViewById(R.id.inches);

        String[] arr = {"Km","m","cm","mm","micm","nm","Mile","Yard","Foot","Inch"};
        selector.setAdapter(new ArrayAdapter<>(LengthActivity.this, android.R.layout.simple_expandable_list_item_1, arr));

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
        if(!input.getText().toString().equals("") && !selector.getSelectedItem().toString().equals(""))
        {
            double in = Double.parseDouble(input.getText().toString());  //in IS THE INPUT PLACED ON THE TEXTBOX
            switch (selector.getSelectedItem().toString())
            {   // THE VALUES BEING USE HERE ARE IN KILOMETRES.SIMPLE CONVERSIONS ARE DONE.
                case "Km":                 // CASE POINT WHEN THE SELECTED VALUE IS IN KILOMETRES-BY DEFAULT
                    setKm(in);
                    break;

                case "m":                  // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setKm(in/1000);        // 1KM = 1000m
                    break;

                case "cm":                 // CASE POINT WHEN THE SELECTED VALUE IS IN CENTIMETRES
                    setKm(in/100000);      // 1KM = 100000cm
                    break;

                case "mm":                 // CASE POINT WHEN THE SELECTED VALUE IS IN MILLIMETRES
                    setKm(in/1000000);     // 1KM = 1000000mm
                    break;

                case "micm":                 // CASE POINT WHEN THE SELECTED VALUE IS IN MICROMETRES
                    setKm(in/1000000000);    // 1KM = 1000000000micrometres
                    break;

                case "nm":                             // CASE POINT WHEN THE SELECTED VALUE IS IN NANOMETRES
                    int nano = 10000000 * 100000;      // INTRODUCE THE VARIABLE nano DUE TO THE HIGH NUMBER OF ZEROS
                    setKm(in/nano);                    // 1KM = 1000000000 nanometres
                    break;

                case "Mile":               // CASE POINT WHEN THE SELECTED VALUE IS IN MILES
                    setKm(in * 1.609);     // 1Mile = 1.609km
                    break;

                case "Yard":               // CASE POINT WHEN THE SELECTED VALUE IS IN YARDS
                    setKm(in/1094);        // 1KM = 1094yards     1Metre = 1.094 yards
                    break;

                case "Foot":               // CASE POINT WHEN THE SELECTED VALUE IS IN FEET
                    setKm(in/3281);        // 1KM = 3281 Feet     1Metre = 3.281 feet
                    break;

                case "Inch":               // CASE POINT WHEN THE SELECTED VALUE IS IN INCHES
                    setKm(in/39370);       // 1KM = 39370inches   1Inch = 2.54cm    1cm = 0.3937inches
                    break;

            }
        }
    }

    // THIS METHOD IS USED FOR AUTOMATICALLY SETTING EVERY UNIT TO THEIR DESIGNATED VALUE BASED ON MANIPULATION IN KILOMETRES
    private void setKm(double km_in) {
        KM.setText(String.valueOf(km_in));
        M.setText(String.valueOf(km_in * 1000));
        CM.setText(String.valueOf(km_in * 100000));
        MM.setText(String.valueOf(km_in * 1000000));
        MicM.setText(String.valueOf(km_in * 1000000000));
        NM.setText(String.valueOf(km_in * 10000000 * 100000));
        MILE.setText(String.valueOf(km_in / 1.609));
        YARD.setText(String.valueOf(km_in * 1094));
        FEET.setText(String.valueOf(km_in * 3281));
        INCHES.setText(String.valueOf(km_in * 39370));
    }

}