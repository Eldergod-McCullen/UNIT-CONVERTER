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

public class MassActivity extends AppCompatActivity {
    EditText input;
    Spinner selector;
    TextView KG,G,LB,MG,MicG,T,OZ,NG,CARAT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mass);

        input = findViewById(R.id.input);
        selector = findViewById(R.id.unit_selector);
        KG = findViewById(R.id.Kg);
        G = findViewById(R.id.g);
        LB = findViewById(R.id.lb);
        MG = findViewById(R.id.mg);
        MicG = findViewById(R.id.micg);
        T = findViewById(R.id.t);
        OZ = findViewById(R.id.oz);
        NG = findViewById(R.id.ng);
        CARAT = findViewById(R.id.carat);

        String[] arr = {"Kg","g","lb","mg","micg","ng","oz","t","car"};
        selector.setAdapter(new ArrayAdapter<>(MassActivity.this, android.R.layout.simple_expandable_list_item_1, arr));

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
                case "Kg":                 // CASE POINT WHEN THE SELECTED VALUE IS IN KILOGRAMS-BY DEFAULT
                    setKg(in);
                    break;

                case "g":                  // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setKg(in/1000);        // 1KG = 1000g
                    break;

                case "mg":                 // CASE POINT WHEN THE SELECTED VALUE IS IN MILLIGRAMS
                    setKg(in/1000000);     // 1KG = 1000000mG
                    break;

                case "micg":                 // CASE POINT WHEN THE SELECTED VALUE IS IN MICROGRAMS
                    setKg(in/1000000000);    // 1KM = 1000000000micrograms
                    break;

                case "ng":                             // CASE POINT WHEN THE SELECTED VALUE IS IN NANOGRAMS
                    int nano = 10000000 * 100000;      // INTRODUCE THE VARIABLE nano DUE TO THE HIGH NUMBER OF ZEROS
                    setKg(in/nano);                    // 1KM = 1000000000 nanograms
                    break;

                case "lb":                 // CASE POINT WHEN THE SELECTED VALUE IS IN POUNDS
                    setKg(in/2.205);     // 1KG = 2.205LB
                    break;

                case "oz":                     // CASE POINT WHEN THE SELECTED VALUE IS IN OUNCES
                    setKg(in/35.274);        // 1KG = 35.274 ounces
                    break;

                case "t":                 // CASE POINT WHEN THE SELECTED VALUE IS IN TONNES
                    setKg(in * 1000);       // 1T = 1000kg
                    break;

                case "car":               // CASE POINT WHEN THE SELECTED VALUE IS IN INCHES
                    setKg(in/5000);      // 1KG = 5000carats
                    break;

            }
        }
    }

    // THIS METHOD IS USED FOR AUTOMATICALLY SETTING EVERY UNIT TO THEIR DESIGNATED VALUE BASED ON MANIPULATION IN KILOMETRES
    private void setKg(double kg_in) {
        KG.setText(String.valueOf(kg_in));
        G.setText(String.valueOf(kg_in * 1000));
        MG.setText(String.valueOf(kg_in * 1000000));
        MicG.setText(String.valueOf(kg_in * 1000000000));
        NG.setText(String.valueOf(kg_in * 10000000 * 100000));
        LB.setText(String.valueOf(kg_in * 2.205));
        OZ.setText(String.valueOf(kg_in * 35.274));
        T.setText(String.valueOf(kg_in/1000));
        CARAT.setText(String.valueOf(kg_in * 5000));
    }
}