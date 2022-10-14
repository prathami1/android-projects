package com.example.weatherappvolley;

public class Location
{
    String longitude, latitude, name;

    public Location(String longitude, String latitude, String name)
    {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName()
    { return name; }

    public String getLongitude()
    { return longitude; }

    public String getLatitude()
    { return latitude; }
}
