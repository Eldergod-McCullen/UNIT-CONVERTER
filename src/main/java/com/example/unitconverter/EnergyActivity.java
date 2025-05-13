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

public class EnergyActivity extends AppCompatActivity {
    EditText input;
    Spinner selector;
    TextView J,KJ,KH,WH,CAL,HH,NM,WS,TH,KT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_energy);

        input = findViewById(R.id.input);
        selector = findViewById(R.id.unit_selector);
        J = findViewById(R.id.j);
        KJ = findViewById(R.id.kj);
        KH = findViewById(R.id.kh);
        WH = findViewById(R.id.wh);
        CAL = findViewById(R.id.cal);
        HH = findViewById(R.id.hh);
        NM = findViewById(R.id.nm);
        WS = findViewById(R.id.ws);
        TH = findViewById(R.id.th);
        KT = findViewById(R.id.kt);

        String[] arr = {"J","kJ","kWh","Wh","cal","hph","Nm","Ws","ton-hour","kton"};
        selector.setAdapter(new ArrayAdapter<>(EnergyActivity.this, android.R.layout.simple_expandable_list_item_1, arr));

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
            switch (selector.getSelectedItem().toString()) {   // THE VALUES BEING USE HERE ARE IN KILOMETRES.SIMPLE CONVERSIONS ARE DONE.
                case "J":                 // CASE POINT WHEN THE SELECTED VALUE IS IN KILOMETRES-BY DEFAULT
                    setJ(in);
                    break;

                case "kJ":                  // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setJ(in / 0.001);        // 1KM = 1000m
                    break;

                case "kWh":                                // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    double kilwh = 1 * 2.777 * 0.0000001;
                    setJ(in / kilwh);                        // 1KM = 1000m
                    break;

                case "Wh":                 // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setJ(in / 0.0002777778);        // 1KM = 1000m
                    break;

                case "cal":                 // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    setJ(in / 0.2388458966);        // 1KM = 1000m
                    break;

                case "hph":                 // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    double hoph = 3.7251 * 0.0000001;
                    setJ(in / hoph);        // 1KM = 1000m
                    break;

                case "Nm":                 // CASE POINT WHEN THE SELECTED VALUE IS IN METRES

                case "Ws":
                    setJ(in / 1);        // 1KM = 1000m
                    break;               // MERGED WITH NM

                case "ton-hour":                 // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    double tonh = 7.898 * 0.00000001;
                    setJ(in / tonh);                           // 1KM = 1000m
                    break;

                case "kton":                 // CASE POINT WHEN THE SELECTED VALUE IS IN METRES
                    double kilot = 2.3901 * 0.0000000000001;
                    setJ(in / kilot);        // 1KM = 1000m
                    break;
            }
        }
    }

// THIS METHOD IS USED FOR AUTOMATICALLY SETTING EVERY UNIT TO THEIR DESIGNATED VALUE BASED ON MANIPULATION IN KILOMETRES
              private void setJ(double J_in) {
                  J.setText(String.valueOf(J_in));
                  KJ.setText(String.valueOf(J_in * 0.001));
                  KH.setText(String.valueOf(J_in*(2.777 * 0.0000001)));
                  WH.setText(String.valueOf(J_in * 0.0002777778));
                  CAL.setText(String.valueOf(J_in * 0.2388458966));
                  HH.setText(String.valueOf(J_in * 3.7251 * 0.0000001));
                  NM.setText(String.valueOf(J_in * 1));
                  WS.setText(String.valueOf(J_in * 1));
                  TH.setText(String.valueOf(J_in * 7.898 * 0.00000001));
                  KT.setText(String.valueOf(J_in*(2.3901 * 0.0000000000001)));
              }
}