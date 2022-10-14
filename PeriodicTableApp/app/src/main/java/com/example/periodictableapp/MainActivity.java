package com.example.periodictableapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MainActivity extends AppCompatActivity
{
    ListView listView;
    CustomAdapter adapter;
    ArrayList<Element> list;

    //portrait elements
    TextView extCategory;
    TextView extPhase;
    Button shuffleP;

    //API arrayLists
    ArrayList<String> symbols;
    ArrayList<String> names;
    ArrayList<Integer> img;

    //API testing
    private RequestQueue requestQueue;

    //landscape elements
    TextView LSymbol;
    TextView LName;
    TextView LCategory;
    TextView LDiscovered;
    TextView LNamed;
    TextView LSummary;
    TextView LPhase;
    Button shuffleL;

    //iteration variable
    int extPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.id_list);

        //portrait views
        extCategory = findViewById(R.id.id_category);
        shuffleP = findViewById(R.id.id_shuffle);
        extPhase = findViewById(R.id.id_phase);

        //landscape views
        LSymbol = findViewById(R.id.id_layout_symbol);
        LName = findViewById(R.id.id_layout_name);
        LCategory = findViewById(R.id.id_layout_category);
        LDiscovered = findViewById(R.id.id_layout_discoveredBy);
        LNamed = findViewById(R.id.id_layout_namedBy);
        LSummary = findViewById(R.id.id_layout_summary);
        LPhase = findViewById(R.id.id_layout_phase);
        shuffleL = findViewById(R.id.id_land_shuffle);

        list = new ArrayList<Element>();
        symbols = new ArrayList<>();
        names = new ArrayList<>();
        img = new ArrayList<>();

        //image initialization
        img.add(R.drawable.hydrogen);
        img.add(R.drawable.helium);
        img.add(R.drawable.lithium);
        img.add(R.drawable.beryllium);
        img.add(R.drawable.boron);
        img.add(R.drawable.carbon);
        img.add(R.drawable.nitrogen);
        img.add(R.drawable.oxygen);
        img.add(R.drawable.flourine);
        img.add(R.drawable.neon);
        img.add(R.drawable.sodium);
        img.add(R.drawable.magnesium);
        img.add(R.drawable.aluminum);
        img.add(R.drawable.silicon);
        img.add(R.drawable.phoshorus);
        img.add(R.drawable.sulfur);
        img.add(R.drawable.chlorine);
        img.add(R.drawable.argon);
        img.add(R.drawable.potassium);
        img.add(R.drawable.calcium);
        img.add(R.drawable.scandium);
        img.add(R.drawable.titanium);
        img.add(R.drawable.vanadium);
        img.add(R.drawable.chromium);
        img.add(R.drawable.manganese);
        img.add(R.drawable.iron);
        img.add(R.drawable.cobalt);
        img.add(R.drawable.nickel);
        img.add(R.drawable.copper);
        img.add(R.drawable.zinc);
        img.add(R.drawable.gallium);
        img.add(R.drawable.germanium);
        img.add(R.drawable.arsenic);
        img.add(R.drawable.selenium);
        img.add(R.drawable.bromine);
        img.add(R.drawable.krypton);
        img.add(R.drawable.rubidium);
        img.add(R.drawable.strontium);
        img.add(R.drawable.yttrium);
        img.add(R.drawable.zirconium);
        img.add(R.drawable.niobium);
        img.add(R.drawable.molybdenum);
        img.add(R.drawable.technetium);
        img.add(R.drawable.ruthenium);
        img.add(R.drawable.rhodium);
        img.add(R.drawable.palladium);
        img.add(R.drawable.silver);
        img.add(R.drawable.cadmium);
        img.add(R.drawable.indium);
        img.add(R.drawable.tin);
        img.add(R.drawable.antimony);
        img.add(R.drawable.tellurium);
        img.add(R.drawable.iodine);
        img.add(R.drawable.xenon);
        img.add(R.drawable.caesium);
        img.add(R.drawable.barium);
        img.add(R.drawable.praseodymium);
        img.add(R.drawable.neodymium);
        img.add(R.drawable.promethium);
        img.add(R.drawable.samarium);
        img.add(R.drawable.europium);
        img.add(R.drawable.gadolinium);
        img.add(R.drawable.terbium);
        img.add(R.drawable.dysprosium);
        img.add(R.drawable.holmium);
        img.add(R.drawable.erbium);
        img.add(R.drawable.thulium);
        img.add(R.drawable.ytterbium);
        img.add(R.drawable.lutetium);
        img.add(R.drawable.hafnium);
        img.add(R.drawable.tantalum);
        img.add(R.drawable.tungsten);
        img.add(R.drawable.rhenium);
        img.add(R.drawable.osmium);
        img.add(R.drawable.iridium);
        img.add(R.drawable.platinum);
        img.add(R.drawable.gold);
        img.add(R.drawable.mercury);
        img.add(R.drawable.thallium);
        img.add(R.drawable.lead);
        img.add(R.drawable.bismuth);
        img.add(R.drawable.polonium);
        img.add(R.drawable.astatine);
        img.add(R.drawable.radon);
        img.add(R.drawable.francium);
        img.add(R.drawable.radium);
        img.add(R.drawable.actinium);
        img.add(R.drawable.thorium);
        img.add(R.drawable.protactinium);
        img.add(R.drawable.uranium);
        img.add(R.drawable.neptunium);
        img.add(R.drawable.plutonium);
        img.add(R.drawable.americium);
        img.add(R.drawable.curium);
        img.add(R.drawable.berkelium);
        img.add(R.drawable.californium);
        img.add(R.drawable.einsteinium);
        img.add(R.drawable.fermium);
        img.add(R.drawable.mendelevium);
        img.add(R.drawable.nobelium);
        img.add(R.drawable.lawrencium);
        img.add(R.drawable.rutherfordium);
        img.add(R.drawable.dubnium);
        img.add(R.drawable.seaborgium);
        img.add(R.drawable.bohrium);
        img.add(R.drawable.hassium);
        img.add(R.drawable.meitnerium);
        img.add(R.drawable.darmstadtium);
        img.add(R.drawable.roentgenium);
        img.add(R.drawable.copernicium);
        img.add(R.drawable.nihonium);
        img.add(R.drawable.flerovium);
        img.add(R.drawable.moscovium);
        img.add(R.drawable.livermorium);
        img.add(R.drawable.tennessine);
        img.add(R.drawable.oganesson);

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        if(savedInstanceState != null)
        {
            list = (ArrayList<Element>) savedInstanceState.getSerializable("SAVE");
            symbols = (ArrayList<String>) savedInstanceState.getSerializable("SYMBOLS");
            names = (ArrayList<String>) savedInstanceState.getSerializable("NAMES");
            img = (ArrayList<Integer>) savedInstanceState.getSerializable("IMAGES");
        }
        else
        { fetchData(); }

        adapter = new CustomAdapter(this, R.layout.adapter_custom, list);
        listView.setAdapter(adapter);
        //listView.setItemsCanFocus(true);
        //adapter.notifyDataSetChanged();

        if(getResources().getConfiguration().orientation == 1)
        {
            shuffleP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shuffleP.setText("shuffle");
                    Collections.shuffle(list);
                    adapter.notifyDataSetChanged();
                    //onSaveInstanceState(savedInstanceState);
                }
            });
        }
        if(getResources().getConfiguration().orientation == 2)
        {
            shuffleL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Collections.shuffle(list);
                    adapter.notifyDataSetChanged();
                    //onSaveInstanceState(savedInstanceState);
                }
            });
        }
    }

    public void fetchData()
    {
        String url = "https://raw.githubusercontent.com/prathami1/data/main/periodic-table/data.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                for(int i = 0; i < response.length(); i++)
                {
                    try
                    {
                        JSONObject jsonObject = response.getJSONObject(i);
                        //Strings to record
                        String name = jsonObject.getString("name");
                        String appearance = jsonObject.getString("appearance");
                        Double aMass = jsonObject.getDouble("atomic_mass");
                        Double bPoint = jsonObject.getDouble("boil");
                        String category = jsonObject.getString("category");
                        Double density = jsonObject.getDouble("density");
                        String discoveredBy = jsonObject.getString("discovered_by");
                        Double mPoint = jsonObject.getDouble("melt");
                        Double molarH = jsonObject.getDouble("molar_heat");
                        String namedBy = jsonObject.getString("named_by");
                        int atomicN = jsonObject.getInt("number");
                        String phase = jsonObject.getString("phase");
                        String spectralImg = jsonObject.getString("spectral_img");
                        String summary = jsonObject.getString("summary");
                        String symbol = jsonObject.getString("symbol");

                        list.add(new Element(name, symbol, atomicN, img.get(i), category, discoveredBy, namedBy, appearance, phase, summary, spectralImg, aMass, bPoint, density, mPoint, molarH));
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                        Log.d("catch", "API can't retrieve data");
                    }
                }
                Log.d("NAMES", "Name: " + list.get(0).getName());
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            { Toast.makeText (MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show(); }
        });

        requestQueue.add(jsonArrayRequest);
    }

    public class CustomAdapter extends ArrayAdapter<Element>
    {
        Context mainContext;
        List<Element> myList;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Element> objects)
        {
            super(context, resource, objects);
            mainContext = context;
            myList = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            //return super.getView(position, convertView, parent);
            LayoutInflater layoutInflater = (LayoutInflater) mainContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.adapter_custom, null);
            TextView name = view.findViewById(R.id.id_name);
            TextView aNumber = view.findViewById(R.id.id_atomic_number);
            TextView symbol = view.findViewById(R.id.id_symbol);
            ImageView image = view.findViewById(R.id.id_image);
            Button removeB = view.findViewById(R.id.id_removeB);

            aNumber.setText("Atomic Number: " + myList.get(position).getAtomicN());
            name.setText(myList.get(position).getName());
            symbol.setText(myList.get(position).getSymbol());
            image.setImageResource(myList.get(position).getDrawable());

            removeB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int count = listView.getPositionForView((View)v.getParent());
                    list.remove(count);
                    adapter.notifyDataSetChanged();
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getResources().getConfiguration().orientation == 1)
                    {
                        extCategory.setText("Category: " + myList.get(position).getCategory());
                        extPhase.setText("Phase: " + myList.get(position).getPhase());
                        extPosition = position;

                        //detailed activity
                        /*Intent intent = new Intent(mainContext, DetailedActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("Name", myList.get(position).getName());
                        bundle.putString("Symbol", myList.get(position).getSymbol());

                        intent.putExtras(bundle);

                        mainContext.startActivity(intent);*/
                    }
                    if(getResources().getConfiguration().orientation == 2)
                    {
                        LSymbol.setText(myList.get(position).getSymbol());
                        LPhase.setText("(" + myList.get(position).getPhase() + ")");
                        LName.setText(myList.get(position).getName());
                        LCategory.setText("Category: " + myList.get(position).getCategory());
                        LDiscovered.setText("Discovered By: " + myList.get(position).getDiscoveredBy());
                        if(myList.get(position).getNamedBy() != "null")
                        { LNamed.setText("Named By: " + myList.get(position).getNamedBy()); }
                        else
                        { LNamed.setText("Named By: Unknown"); }
                        LSummary.setText("Summary: " + myList.get(position).getSummary());
                        extPosition = position;
                    }
                }
            });
            return view;
        }
    }

    //saved state while rotating
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("SAVE", list);
        outState.putSerializable("SYMBOLS", symbols);
        outState.putSerializable("NAMES", names);
        outState.putSerializable("IMAGES", img);
        outState.putInt("position", extPosition);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle state)
    {
        super.onRestoreInstanceState(state);
        extPosition = state.getInt("position");
        System.out.println(extPosition);
        if(getResources().getConfiguration().orientation == 1)
        {
            extCategory.setText("Category: " + list.get(extPosition).getCategory());
            extPhase.setText("Phase: " + list.get(extPosition).getPhase());
        }
        if(getResources().getConfiguration().orientation == 2)
        {
            LSymbol.setText(list.get(extPosition).getSymbol());
            LName.setText(list.get(extPosition).getName());
            LPhase.setText("(" + list.get(extPosition).getPhase() + ")");
            LCategory.setText("Category: " + list.get(extPosition).getCategory());
            LDiscovered.setText("Discovered By: " + list.get(extPosition).getDiscoveredBy());
            if(list.get(extPosition).getNamedBy() != "null")
            { LNamed.setText("Named By: " + list.get(extPosition).getNamedBy()); }
            else
            { LNamed.setText("Named By: Unknown"); }
            LSummary.setText("Summary: " + list.get(extPosition).getSummary());
        }
    }
}

