package database;

import database.filter.Filter;
import database.filter.FilterType;
import database.searching.BinarySearch;
import database.sorting.SelectionSort;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chris on 08.05.2018
 */
public class SimpleDatabase {

    private static final String CHARSET = "ISO-8859-1";

    private static final String SYMBOL_DELIMITER = ";";
    private static final String SYMBOL_DEFAULT_VALUE = "/";
    private static final String SYMBOL_DECIMAL_ENGLISH = ",";
    private static final String SYMBOL_DECIMAL_GERMAN = ".";
    private static final String SYMBOL_SPACE = " ";
    private static final String SYMBOL_EMPTY = "";

    private static final String DEFAULT_VALUE = "0";

    public SimpleDatabase() {
    }

    /**
     * Diese Methode stellt das Einlesen von Städten eines Abschnitts einer übergebenen CSV-Datei von Start-Zeile
     * bis End-Zeile da. Die CSV-Datei dient dabei als simple Datenbank. Eine Zeile der CSV-Datei stellt dabei ein
     * Objekt der Klasse City da.
     *
     * @param path  Pfad der CSV-Datei
     * @param start start > 0
     * @param end   end >= start
     * @return falls Datei nicht gefunden oder Fehler beim einlesen eine leere Liste, sonst Cities von Start bis End
     * @throws IOException
     */
    public List<City> load(String path, int start, int end) {

        // 1. preconditions check
        if (path == null) throw new NullPointerException("path must not be empty");
        if (!(start > 0)) throw new IllegalArgumentException("start > 0");
        if (!(end >= start)) throw new IllegalArgumentException("end >= start");


        // 2. Einlesen der CSV-Datein und Aufbauen der City-Liste
        ArrayList<City> result = new ArrayList<City>();

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), CHARSET));
            try {

                int index = 0;
                String line;

                // 2.1 Lese jede Zeile ein
                while ((line = reader.readLine()) != null) {

                    // 2.2 Prüfe ob die Zeile im Intervall liegt.
                    index += 1;
                    if (index < start) continue;
                    if (index > end) break;

                    // 2.3 Trenne die Spalten von einander
                    String tokens[] = line.split(SYMBOL_DELIMITER);

                    // 2.4 Entferne die Spaces aus den Zahlenwerten
                    for (int i = 1; i < tokens.length; i++) {
                        tokens[i] = this.replaceSpace(tokens[i]);
                    }

                    // 2.5 Erstelle für die Zeile ein City und füge es der City-Liste hinzu
                    result.add(new City(
                            tokens[0],
                            this.parseIntValue(tokens[1]),
                            this.parseDoubleValue(tokens[2]),
                            this.parseIntValue(tokens[3]),
                            this.parseIntValue(tokens[4]),
                            this.parseIntValue(tokens[5])
                    ));

                }

            } finally {
                reader.close();
            }

        } catch (IOException e) {
            System.err.println(e.getClass().getSimpleName() + " - return empty list");
        }

        return result;
    }


    /**
     * Diese Hilfsmethode entfernt alle Spaces aus einem übergebenen String
     *
     * @param string darf nicht null sein
     * @return falls kein Symbol Space vorhanden dann keine Veränderung
     */
    private String replaceSpace(String string) {
        if(string == null) throw new IllegalArgumentException();

        return string.replace(SYMBOL_SPACE, SYMBOL_EMPTY).replace(SYMBOL_DEFAULT_VALUE, DEFAULT_VALUE);
    }

    /**
     * Diese Hilfsmethode konvertiert einen String zu einem Integer-Value
     *
     * @param string darf nicht null sein und muss einen Integer-Wert darstellen
     * @return Integer-Wert des Strings
     * @exception NumberFormatException falls der String keinen Integer-Wert darstellt
     */
    private Integer parseIntValue(String string) {
        if(string == null) throw new IllegalArgumentException();

        return Integer.valueOf(string);
    }

    /**
     * Diese Hilfsmethode konvertiert einen String zu einem Double-Value
     *
     * @param string darf nicht null sein und muss einen Double-Wert darstellen
     * @return Double-Wert des Strings
     * @exception NumberFormatException falls der String keinen Double-Wert darstellt
     */
    private Double parseDoubleValue(String string) {
        if(string == null) throw new IllegalArgumentException();

        return Double.valueOf(string.replace(SYMBOL_DECIMAL_ENGLISH, SYMBOL_DECIMAL_GERMAN));
    }


    public List<City> find(List<Filter> filterList, List<City> database) {

        // 1. Ergebnisliste = alles
        List<City> result = database;


        // 5. So lange 2 - 4 bis alle Filter abgearbeitet sind
        for (int i = 0; i < filterList.size(); i++) {

            // 2. Für aktuellen Filter Hilfsliste aus Ergebnisliste erstellen (Sortierung)
            result = new SelectionSort().sort(result, filterList.get(i).getFilterType());

            // 3. Binäre Suche nach Wert von links nach rechts Intervall
            int leftIndex = new BinarySearch().searchLeft(result, filterList.get(i).getFilterType(), filterList.get(i).getStart(), true);
            int rightIndex = new BinarySearch().searchRight(result, filterList.get(i).getFilterType(), filterList.get(i).getEnd(), false);

            // 4. Ergebnisliste aktualisieren mit Hilfe der Hilfsliste
            // Bsp: new Filter(FilterType.PLZ, 101, 120) leftIndex 1 = 200 bis rightIndex 0 = 100, dann leere liste
            // weil subList 1,1 leer, weil das zweite 1 exlusiv bedeutet
            result = result.subList(leftIndex, rightIndex + 1);

        }

        return result;
    }


    public static void main(String[] args) throws IOException {

        SimpleDatabase database = new SimpleDatabase();

        List<City> cities = database.load("database/StaedteStatistik.CSV", 1, 2);

        List<Filter> filters = Arrays.asList(
                //new Filter(FilterType.PLZ, 58675, 59968) // TODO - 552670, 55435
                //new Filter(FilterType.POPULATION, 500000, 3000000)
                //new Filter(FilterType.AREA, 13, 20)

        );

        List<City> result = database.find(filters, cities);

        System.out.println(result);

        /*
        //List<City> result = database.find(filters, cities);

        //System.out.println(result);

        List<City> dumies = new ArrayList<City>();
        dumies.add(new City(10));
        dumies.add(new City(20));
        dumies.add(new City(40));
        dumies.add(new City(50));
        dumies.add(new City(70));
        dumies.add(new City(80));


        filters = Arrays.asList(
                new Filter(FilterType.PLZ, 49, 80)

        );

        List<City> result = database.find(filters, dumies);

        System.out.println(result);
        */


    }
}
