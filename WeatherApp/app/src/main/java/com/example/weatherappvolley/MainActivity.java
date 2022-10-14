package com.example.weatherappvolley;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private RequestQueue requestQueue;
    Location Location;
    ArrayList<HourlyForecast> HourlyForecastList;
    Button execute;
    EditText input;
    TextView title, longlat, tempText, feelsLikeText, descText, date;
    ListView listView;

    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        execute = findViewById(R.id.id_search);
        input = findViewById(R.id.id_inputZip);
        longlat = findViewById(R.id.id_LongLat);
        title = findViewById(R.id.id_title);
        tempText = findViewById(R.id.id_temp);
        feelsLikeText = findViewById(R.id.id_feelsLike);
        descText = findViewById(R.id.id_desc);
        listView = findViewById(R.id.id_listView);
        date = findViewById(R.id.id_date);

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        HourlyForecastList = new ArrayList<>();

        input.setGravity(Gravity.CENTER_HORIZONTAL);
        input.setHint("Enter Zipcode");

        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchWeather(fetchCoordinates(input.getText().toString(), "us"));
                HourlyForecastList.clear();
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new CustomAdapter(this, R.layout.adapter_custom, HourlyForecastList);
        listView.setAdapter(adapter);
    }

    public Location fetchCoordinates(String zipCode, String countryCode)
    {
        String key = "8f08597c0d79018f8d3e5e84ceed71b3";
        String GeoUrl = "https://api.openweathermap.org/geo/1.0/zip?zip=" + zipCode + "," + countryCode + "&appid=" + key;

        JsonObjectRequest GeoObjectRequest = new JsonObjectRequest(Request.Method.GET, GeoUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try
                {
                    String name = response.getString("name");
                    String latitude = response.getString("lat");
                    String longitude = response.getString("lon");

                    Location = new Location(longitude, latitude, name);
                    title.setText(name);
                    longlat.setText("Longitude: " + longitude + " Latitude: " + latitude);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.d("catch", "API can't retrieve data");
                    Toast.makeText (MainActivity.this, "Error: Cannot Retrieve Data from Geocoding API", Toast.LENGTH_SHORT).show();
                }
            }
        },new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            { Toast.makeText (MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show(); }
        });
        requestQueue.add(GeoObjectRequest);
        return Location;
    }

    public void fetchWeather(Location Location)
    {
        //Location = fetchCoordinates(zipCode, countryCode);
        String key = "{API_KEY}", exclude = "daily";
        String WeatherUrl = "https://api.openweathermap.org/data/2.5/onecall?lat=" + Location.getLatitude() + "&lon=" + Location.getLongitude() + "&exclude=" + exclude + "&units=imperial&appid=" + key;

        JsonObjectRequest WeatherObjectRequest = new JsonObjectRequest(Request.Method.GET, WeatherUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try
                {
                    JSONObject current = response.getJSONObject("current");

                    //weather statistics fetch (all time)
                    Long timeUnix = Long.parseLong(current.getString("dt"));
                    String sunrise = current.getString("sunrise");
                    String sunset = current.getString("sunset");
                    String temp = current.getString("temp");
                    String feelsLike = current.getString("feels_like");
                    String pressure = current.getString("pressure");
                    String dewPoint = current.getString("humidity");
                    String clouds = current.getString("clouds");
                    String visibility = current.getString("visibility");
                    String windSpeed = current.getString("wind_speed");
                    String windDeg = current.getString("wind_deg");


                    SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm aa, MM/dd/yyyy");
                    sdf.setTimeZone(java.util.TimeZone.getTimeZone("EST"));
                    Date time = new java.util.Date(timeUnix*1000L);
                    String formattedTime = sdf.format(time);
                    date.setText(formattedTime);

                    tempText.setText(temp + "° F");
                    feelsLikeText.setText("Feels Like: " + feelsLike + "° F");

                    //weather description fetch
                    JSONArray weatherArray = current.getJSONArray("weather");
                    JSONObject weatherObject = weatherArray.getJSONObject(0);
                    String mainDesc = weatherObject.getString("main");
                    String desc = weatherObject.getString("description");
                    String icon = weatherObject.getString("icon");

                    descText.setText(mainDesc + " (" + desc + ")");

                    //weather hourly forecast fetch
                    JSONArray hourly = response.getJSONArray("hourly");
                    for(int i = 0; i < 5; i++)
                    {
                        JSONObject hourObject =  hourly.getJSONObject(i);
                        Long timeHourly = Long.parseLong(hourObject.getString("dt"));
                        String tempHourly = hourObject.getString("temp");
                        String feelsLikeHourly = hourObject.getString("feels_like");
                        String pressureHourly = hourObject.getString("pressure");
                        String dewPointHourly = hourObject.getString("humidity");
                        String cloudsHourly = hourObject.getString("clouds");
                        String visibilityHourly = hourObject.getString("visibility");
                        String windSpeedHourly = hourObject.getString("wind_speed");
                        String windDegHourly = hourObject.getString("wind_deg");

                        JSONArray weatherArrayH = hourObject.getJSONArray("weather");
                        JSONObject weatherObjectH = weatherArrayH.getJSONObject(0);
                        String iconH = weatherObjectH.getString("icon");

                        Date timeH = new java.util.Date(timeHourly*1000L);
                        SimpleDateFormat sdfH = new java.text.SimpleDateFormat("hh:mm aa");
                        sdfH.setTimeZone(java.util.TimeZone.getTimeZone("EST"));
                        String formattedTimeH = sdfH.format(timeH);

                        HourlyForecastList.add(new HourlyForecast(formattedTimeH, tempHourly, feelsLikeHourly, pressureHourly, dewPointHourly, cloudsHourly, visibilityHourly, windSpeedHourly, windDegHourly, iconH));
                    }
                    adapter.notifyDataSetChanged();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    Log.d("catch", "API can't retrieve data");
                    Toast.makeText (MainActivity.this, "Error: Cannot Retrieve Data from Geocoding API", Toast.LENGTH_SHORT).show();
                }
            }
        },new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            { Toast.makeText (MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show(); }
        });

        requestQueue.add(WeatherObjectRequest);
        adapter.notifyDataSetChanged();
    }

    public class CustomAdapter extends ArrayAdapter<HourlyForecast> {

        Context mainContext;
        List<HourlyForecast> myList;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<HourlyForecast> objects)
        {
            super(context, resource, objects);
            mainContext = context;
            myList = objects;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) mainContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterView = layoutInflater.inflate(R.layout.adapter_custom, null);
            TextView dateTxt = adapterView.findViewById(R.id.id_adapter_hour);
            TextView lowTxt = adapterView.findViewById(R.id.id_adapter_temp);
            TextView highTxt = adapterView.findViewById(R.id.id_adapter_feelsLike);
            ImageView icon = adapterView.findViewById(R.id.id_adapter_imageview);

            String iconUrl = "https://openweathermap.org/img/wn/" + myList.get(position).getIconH() + "@2x.png";
            Picasso.with(MainActivity.this).load(iconUrl).into(icon);

            dateTxt.setText(myList.get(position).getTimeHourly());
            lowTxt.setText(myList.get(position).getTempHourly() + "° F");
            highTxt.setText(myList.get(position).getFeelsLikeHourly() + "° F");

            return adapterView;
        }
    }

}
