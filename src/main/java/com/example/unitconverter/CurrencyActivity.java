package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class CurrencyActivity extends AppCompatActivity {
    private Spinner spinnerFrom, spinnerTo;
    private EditText editTextAmount;
    private TextView textViewResult;
    private Button btnConvert;
    private HashMap<String, Double> currencyRates;

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/7bf57029cbb42623c60c088c/latest/USD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_currency);

        spinnerFrom = findViewById(R.id.spinner_from_currency);
        spinnerTo = findViewById(R.id.spinner_to_currency);
        editTextAmount = findViewById(R.id.editText_amount);
        btnConvert = findViewById(R.id.btn_convert);
        textViewResult = findViewById(R.id.textView_result);

        loadCurrencies();

        btnConvert.setOnClickListener(v -> convertCurrency());

        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        */

    }
// THIS FUNCTION/METHOD LOADS CURRENCIES/EXCHANGE RATES FROM AN API ON THE INTERNET
    private void loadCurrencies() {
        new Thread(() -> {
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) new URL(API_URL).openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuilder json = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }

                currencyRates = parseCurrencyRates(new JSONObject(json.toString()).getJSONObject("conversion_rates"));
                runOnUiThread(this::setupSpinners);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private HashMap<String, Double> parseCurrencyRates(JSONObject rates) throws JSONException {
        HashMap<String, Double> ratesMap = new HashMap<>();
        for (int i = 0; i < rates.names().length(); i++) {
            String currencyCode = rates.names().getString(i);
            ratesMap.put(currencyCode, rates.getDouble(currencyCode));
        }
        return ratesMap;
    }

    private void setupSpinners() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencyRates.keySet().toArray(new String[0]));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
    }

    private void convertCurrency() {
        String fromCurrency = spinnerFrom.getSelectedItem().toString();
        String toCurrency = spinnerTo.getSelectedItem().toString();
        String amountStr = editTextAmount.getText().toString();

        if (amountStr.isEmpty()) {
            textViewResult.setText("Please enter an amount");
            return;
        }

        double amount = Double.parseDouble(amountStr);
        double convertedAmount = (amount / currencyRates.get(fromCurrency)) * currencyRates.get(toCurrency);
        textViewResult.setText(String.format("Result: %.2f %s", convertedAmount, toCurrency));
    }
}