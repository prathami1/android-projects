package com.example.weatherappvolley;

public class HourlyForecast
{
    String timeHourly, tempHourly, feelsLikeHourly, pressureHourly, dewPointHourly, cloudsHourly, visibilityHourly, windSpeedHourly, windDegHourly, iconH;

    public HourlyForecast(String timeHourly, String tempHourly, String feelsLikeHourly, String pressureHourly, String dewPointHourly, String cloudsHourly, String visibilityHourly, String windSpeedHourly, String windDegHourly, String iconH)
    {
        this.timeHourly = timeHourly;
        this.tempHourly = tempHourly;
        this.feelsLikeHourly = feelsLikeHourly;
        this.pressureHourly = pressureHourly;
        this.dewPointHourly = dewPointHourly;
        this.cloudsHourly = cloudsHourly;
        this.visibilityHourly = visibilityHourly;
        this.windSpeedHourly = windSpeedHourly;
        this.windDegHourly = windDegHourly;
        this.iconH = iconH;
    }

    public String getTimeHourly() {
        return timeHourly;
    }

    public String getTempHourly() {
        return tempHourly;
    }

    public String getFeelsLikeHourly() {
        return feelsLikeHourly;
    }

    public String getPressureHourly() {
        return pressureHourly;
    }

    public String getDewPointHourly() {
        return dewPointHourly;
    }

    public String getCloudsHourly() {
        return cloudsHourly;
    }

    public String getVisibilityHourly() {
        return visibilityHourly;
    }

    public String getWindSpeedHourly() {
        return windSpeedHourly;
    }

    public String getWindDegHourly() {
        return windDegHourly;
    }

    public String getIconH() { return iconH; }
}
