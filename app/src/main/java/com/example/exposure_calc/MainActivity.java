package com.example.exposure_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private SeekBar isoSeekBar;
    private TextView isoTextView;
    private SeekBar shutterSpeedSeekBar;
    private TextView shutterSpeedTextView;
    private Spinner apertureSpinner;
    private Button calculateButton;
    private TextView resultTextView;

    private int iso = 400;
    private int shutterSpeed = 500;
    private double aperture = 1.8;

    private static final String[] apertureValues = {"1.0", "1.4", "2.0", "2.8", "4.0", "5.6", "8.0", "11.0", "16.0", "22.0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isoSeekBar = findViewById(R.id.iso_seek_bar);
        isoTextView = findViewById(R.id.iso_text_view);
        shutterSpeedSeekBar = findViewById(R.id.shutter_speed_seek_bar);
        shutterSpeedTextView = findViewById(R.id.shutter_speed_text_view);
        apertureSpinner = findViewById(R.id.aperture_spinner);
        calculateButton = findViewById(R.id.calculate_button);
        resultTextView = findViewById(R.id.result_text_view);

        // Set up the ISO SeekBar
        isoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                iso = progress;
                isoTextView.setText("ISO: " + iso);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Set up the Shutter Speed SeekBar
        shutterSpeedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                shutterSpeed = progress;
                shutterSpeedTextView.setText("Shutter Speed: 1/" + shutterSpeed + "s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Set up the Aperture Spinner
        ArrayAdapter<String> apertureAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, apertureValues);
        apertureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        apertureSpinner.setAdapter(apertureAdapter);
        apertureSpinner.setSelection(2); // set default aperture value
        apertureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aperture = Double.parseDouble(apertureValues[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Set up the Calculate Button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double ev = Math.log10(aperture * aperture / shutterSpeed) + Math.log10(iso / 100.0);
                DecimalFormat df = new DecimalFormat("#.##");
                resultTextView.setText("Exposure: " + df.format(ev) + " EV");
            }
        });
    }
}


