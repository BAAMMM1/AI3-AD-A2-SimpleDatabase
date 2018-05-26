package database;

import database.filter.FilterType;

public class City{

    private String stadt;
    private int postleitzahl;
    private double flaeche;
    private int bevGesamt;
    private int bevMale;
    private int bevFemale;


    public City(String stadt, int postleitzahl, double flaeche, int bevGesamt, int bevMale, int bevFemale) {
        this.stadt = stadt;
        this.postleitzahl = postleitzahl;
        this.flaeche = flaeche;
        this.bevGesamt = bevGesamt;
        this.bevMale = bevMale;
        this.bevFemale = bevFemale;

    }

    @Override
    public String toString() {
        return "\nCity{" +
                "stadt='" + stadt + '\'' +
                ", postleitzahl=" + postleitzahl +
                ", flaeche=" + flaeche +
                ", bevGesamt=" + bevGesamt +
                ", bevMale=" + bevMale +
                ", bevFemale=" + bevFemale +
                '}';
    }

    public String getStadt() {
        return stadt;
    }

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public double getFlaeche() {
        return flaeche;
    }

    public int getBevGesamt() {
        return bevGesamt;
    }

    public int getBevMale() {
        return bevMale;
    }

    public int getBevFemale() {
        return bevFemale;
    }

    public City(int postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public int compareTo(City o, FilterType filterType) {

        if(filterType.equals(FilterType.PLZ)){
            return new Integer(this.getPostleitzahl()).compareTo(new Integer(o.getPostleitzahl()));

        } else if(filterType.equals(FilterType.AREA)) {
            return new Double(this.getFlaeche()).compareTo(new Double(o.getFlaeche()));

        } else if(filterType.equals(FilterType.POPULATION)) {
            return new Integer(this.getBevGesamt()).compareTo(new Integer(o.getBevGesamt()));

        } else if(filterType.equals(FilterType.POPULATION_FEMALE)) {
            return new Integer(this.getBevFemale()).compareTo(new Integer(o.getBevFemale()));

        } else if(filterType.equals(FilterType.POPULATION_MALE)) {
            return new Integer(this.getBevMale()).compareTo(new Integer(o.getBevMale()));

        }

        return 0;
    }

    public double getValue(FilterType filterType){

        if(filterType.equals(FilterType.PLZ)){
            return this.getPostleitzahl();

        } else if(filterType.equals(FilterType.AREA)) {
            return this.getFlaeche();

        } else if(filterType.equals(FilterType.POPULATION)) {
            return this.getBevGesamt();

        } else if(filterType.equals(FilterType.POPULATION_FEMALE)) {
            return this.getBevFemale();

        } else if(filterType.equals(FilterType.POPULATION_MALE)) {
            return this.getBevMale();

        }

        return 0;

    }


}
