package database;

import database.enity.City;
import database.filter.Filter;
import database.filter.FilterType;
import database.searching.BinarySearch;
import database.sorting.SelectionSort;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Diese Klasse stellt eine einfache Datenbank von City-Objekten auf Basis einer CSV-Datei da.
 *
 * @author Chris on 08.05.2018
 */
public class SimpleDatabase {

    /**
     * Gibt an in welchem Charset die CSV-Datei erstellt wurde
     */
    private static final String CHARSET = "ISO-8859-1";

    /**
     * Trennzeichen der CSV-Spalten
     */
    private static final String SYMBOL_DELIMITER = ";";

    /**
     * Symbol in der CSV-Datei für Spalten die keinen Wert enthalten
     */
    private static final String SYMBOL_DEFAULT_VALUE = "/";

    /**
     * Dezimalsymbol Englisch
     */
    private static final String SYMBOL_DECIMAL_ENGLISH = ",";

    /**
     * Dezimalsymbol Deutsch
     */
    private static final String SYMBOL_DECIMAL_GERMAN = ".";

    /**
     * Spacesymbol der CSV-Datei
     */
    private static final String SYMBOL_SPACE = " ";

    /**
     * Symbol für einen leeren String
     */
    private static final String SYMBOL_EMPTY = "";

    /**
     * Default-Wert für die City-Objekte für Spalten in der CSV-Datei die keinen Wert besitzen
     */
    private static final String DEFAULT_VALUE = "0";

    /**
     * Default - Konstruktor
     */
    public SimpleDatabase() { }

    /**
     * Diese Methode stellt das Einlesen von Städten eines Abschnitts einer übergebenen CSV-Datei von Start-Zeile bis
     * End-Zeile da. Die CSV-Datei dient dabei als simple Datenbank. Eine Zeile der CSV-Datei stellt dabei ein Objekt
     * der Klasse City da.
     *
     * @param path  Pfad der CSV-Datei, darf nicht leer sein
     * @param start start > 0
     * @param end   end >= start
     * @return falls Datei nicht gefunden oder Fehler beim einlesen eine leere Liste, sonst Cities von Start bis End
     * @throws IOException falls Pfad oder Charset unbekannt und fehler beim einlesen der CSV-Datei
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
            return result;
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
        if (string == null) throw new IllegalArgumentException();

        return string.replace(SYMBOL_SPACE, SYMBOL_EMPTY).replace(SYMBOL_DEFAULT_VALUE, DEFAULT_VALUE);
    }

    /**
     * Diese Hilfsmethode konvertiert einen String zu einem Integer-Value
     *
     * @param string darf nicht null sein und muss einen Integer-Wert darstellen
     * @return Integer-Wert des Strings
     * @throws NumberFormatException falls der String keinen Integer-Wert darstellt
     */
    private Integer parseIntValue(String string) {
        if (string == null) throw new IllegalArgumentException();

        return Integer.valueOf(string);
    }

    /**
     * Diese Hilfsmethode konvertiert einen String zu einem Double-Value
     *
     * @param string darf nicht null sein und muss einen Double-Wert darstellen
     * @return Double-Wert des Strings
     * @throws NumberFormatException falls der String keinen Double-Wert darstellt
     */
    private Double parseDoubleValue(String string) {
        if (string == null) throw new IllegalArgumentException();

        return Double.valueOf(string.replace(SYMBOL_DECIMAL_ENGLISH, SYMBOL_DECIMAL_GERMAN));
    }


    /**
     * Die Methode find liefert eine Liste von City-Objekten aus der übergebenen Liste an City-Objekten zurück,
     * die alle Kritierien der Filerliste erfüllen.
     *
     * @param filterList darf nicht null sein, falls leer wird kein Filter angewendet
     * @param database   darf nicht null sein, falls leer wird eine leere Liste zurück gegeben
     * @return gefilterte Liste
     */
    public List<City> find(List<Filter> filterList, List<City> database) {

        // 1. precondition check
        if (filterList == null) throw new NullPointerException("filter list must not be null");
        if (database == null) throw new NullPointerException("database must not be null");

        // 2. Ergebnisliste = alles
        List<City> result = database;


        // 6. So lange 3 - 5 bis alle Filter abgearbeitet sind
        for (int i = 0; i < filterList.size(); i++) {

            // 3. Für aktuellen Filter Hilfsliste aus Ergebnisliste erstellen (Sortierung)
            result = new SelectionSort().sort(result, filterList.get(i));

            // 4. Binäre Suche nach Wert von links nach rechts Intervall
            int leftIndex = new BinarySearch().searchLeft(result, filterList.get(i));
            int rightIndex = new BinarySearch().searchRight(result, filterList.get(i));

            // 5. Ergebnisliste aktualisieren mit Hilfe der Hilfsliste
            result = result.subList(leftIndex, rightIndex + 1);

        }

        return result;
    }


    public static void main(String[] args) throws IOException {

        SimpleDatabase database = new SimpleDatabase();

        List<City> cities = database.load("database/StaedteStatistik.CSV", 1, 2058);

        List<Filter> filters = Arrays.asList(
                new Filter(FilterType.PLZ, 1200, 1650), // TODO - 552670, 55435
                new Filter(FilterType.AREA, 26.00, 30.00),
                new Filter(FilterType.POPULATION, 10000, 20000)


        );

        List<City> result = database.find(filters, cities);

        System.out.println(result);


    }
}
