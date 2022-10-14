package com.example.periodictableapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailedActivity extends AppCompatActivity
{
    TextView Tsymbol;
    TextView Tname;
    TextView Tcategory;
    TextView TdiscoveredBy;
    TextView TnamedBy;
    TextView Tappearance;
    TextView TaMass;
    TextView TbPoint;
    TextView Tdensity;
    TextView TmPoint;
    TextView TmHeat;
    TextView Tphase;
    ImageView TspectralImg;
    ImageView TelementPic;
    TextView Tsummary;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        Tsymbol = findViewById(R.id.id_detailed_symbol);
        Tname = findViewById(R.id.id_detailed_name);
        Tcategory = findViewById(R.id.id_detailed_category);
        TdiscoveredBy = findViewById(R.id.id_detailed_discoveredBy);
        TnamedBy = findViewById(R.id.id_detailed_namedBy);
        Tappearance = findViewById(R.id.id_detailed_lin_appearance);
        TaMass = findViewById(R.id.id_detailed_lin_atomicM);
        TbPoint = findViewById(R.id.id_detailed_lin_boilP);
        Tdensity = findViewById(R.id.id_detailed_lin_density);
        TmPoint = findViewById(R.id.id_detailed_lin_melt);
        TmHeat = findViewById(R.id.id_detailed_lin_molarH);
        Tphase = findViewById(R.id.id_detailed_lin_phase);
        TspectralImg = findViewById(R.id.id_detailed_lin_spectralmg);
        TelementPic = findViewById(R.id.id_detailed_imageView);
        Tsummary = findViewById(R.id.id_detailed_summary);

        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("Name");
        String symbol = bundle.getString("symbol");

        //images only
        //Glide.with(this).load(name).into(Tname);
        Tname.setText(name);
        Tsymbol.setText(symbol);
    }
}