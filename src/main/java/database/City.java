package database;

import database.filter.Filter;
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
    public City(String stadt, int postleitzahl, int flaeche, int bevGesamt, int bevMale, int bevFemale) {
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

    public int compareTo(City o, Filter filter) {

        FilterType filterType = filter.getFilterType();

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

        } else {
            throw new IllegalArgumentException("no compare for filter");
        }

    }

    public double getValue(Filter filter){

        if(filter.getFilterType().equals(FilterType.PLZ)){
            return this.getPostleitzahl();

        } else if(filter.getFilterType().equals(FilterType.AREA)) {
            return this.getFlaeche();

        } else if(filter.getFilterType().equals(FilterType.POPULATION)) {
            return this.getBevGesamt();

        } else if(filter.getFilterType().equals(FilterType.POPULATION_FEMALE)) {
            return this.getBevFemale();

        } else if(filter.getFilterType().equals(FilterType.POPULATION_MALE)) {
            return this.getBevMale();

        }  else {
            throw new IllegalArgumentException("no value for filter");
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (postleitzahl != city.postleitzahl) return false;
        if (Double.compare(city.flaeche, flaeche) != 0) return false;
        if (bevGesamt != city.bevGesamt) return false;
        if (bevMale != city.bevMale) return false;
        if (bevFemale != city.bevFemale) return false;
        return stadt != null ? stadt.equals(city.stadt) : city.stadt == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = stadt != null ? stadt.hashCode() : 0;
        result = 31 * result + postleitzahl;
        temp = Double.doubleToLongBits(flaeche);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + bevGesamt;
        result = 31 * result + bevMale;
        result = 31 * result + bevFemale;
        return result;
    }
}
