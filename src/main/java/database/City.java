package database;

public class City {

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

}
