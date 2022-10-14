package com.example.periodictableapp;

public class Element
{
    String name, symbol, category, discoveredBy, namedBy, appearance, phase, summary, spectralImg;
    int atomicN, drawable;
    double aMass, bPoint, density, mPoint, mHeat;

    public Element(String name, String symbol, int atomicN, int drawable, String category, String discoveredBy, String namedBy, String appearance, String phase, String summary, String spectralImg, double aMass, double bPoint, double density, double mPoint, double mHeat)
    {
        this.name = name;
        this.symbol = symbol;
        this.atomicN = atomicN;
        this.drawable = drawable;
        this.category = category;
        this.discoveredBy = discoveredBy;
        this.namedBy = namedBy;
        this.appearance = appearance;
        this.phase = phase;
        this.summary = summary;
        this.spectralImg = spectralImg;
        this.aMass = aMass;
        this.bPoint = bPoint;
        this.density = density;
        this.mPoint = mPoint;
        this.mHeat = mHeat;
    }

    public String getName()
    { return name; }

    public String getSymbol()
    { return symbol; }

    public int getAtomicN()
    { return atomicN; }

    public int getDrawable()
    { return drawable; }

    public String getCategory()
    { return category; }

    public String getDiscoveredBy()
    { return discoveredBy; }

    public String getNamedBy()
    { return namedBy; }

    public String getAppearance()
    { return appearance; }

    public String getPhase()
    { return phase; }

    public String getSummary()
    {return summary; }

    public String getSpectralImg()
    { return spectralImg; }

    public double getaMass()
    { return aMass; }

    public double getbPoint()
    { return bPoint; }

    public double getDensity()
    { return density; }

    public double getmPoint()
    { return mPoint; }

    public double getmHeat()
    { return mHeat; }
}