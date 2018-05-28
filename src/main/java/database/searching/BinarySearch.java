package database.searching;

import database.enity.City;
import database.SimpleDatabase;
import database.filter.Filter;
import database.filter.FilterType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Diese Klasse stellt den Binäre-Suche Algorithmus da. Diese Implementierung läuft immer bis log2(n) zum Ende durch,
 * da sie jeweils die linke bzw die rechte Schranke eines Intervall finden soll.
 *
 * @author Chris on 26.05.2018
 */
public class BinarySearch {

    /**
     * Zählt die Anzahl an Schlüsselvergleichen für die Methode searchLeft()
     */
    private int leftComparison;

    /**
     * Zählt die Anzahl an Schlüsselvergleichen für die Methode searchRight()
     */
    private int rightComparison;

    /**
     * Konstruktor - Default
     */
    public BinarySearch() { }

    /**
     * SearchLeft führt eine binäre Suche durch um die linke Schranke des Intervall des Filter zu finden.
     * Dabei pendelt left in Richtung der Schranke, im letzten Schritt wird Right welches sich dann auf der Schranke
     * befindet erreicht so, dass Right einen niedriger als die linke Schranke geschoben wird.
     * Danach ist die Suche beendet. Die Schranke ist dann eine Position weiter als Right.
     *
     * @param sortedList muss für den Filter vorsortiert sein
     * @param filter Filter der das Intervall enthält
     * @return index des Elementes mit kleinsten index der Liste für das gilt key <= element - Linke Schrank
     */
    public int searchLeft(List<City> sortedList, Filter filter) {

        int left = 0;
        int right = sortedList.size() - 1;
        int mid;
        double leftKey = filter.getStart();
        this.leftComparison = 0;

        // 1. precondition check
        // Wenn die linke Schranke kleiner oder gleich als das erste Element der Liste, dann Fallen keine Objekte raus.
        if (leftKey <= sortedList.get(left).getValue(filter)) return left;

        // Falls die linke Schranke größer als das größe Element ist, dann kein Ergebnis
        if (leftKey > sortedList.get(right).getValue(filter)) throw new IllegalArgumentException("start is to big");

        // 2. So lange bis lins kleiner oder größer right
        while (left <= right) {
            mid = (left + right) / 2;

            // 3. Zählen der Schlüsselvergleiche
            this.leftComparison++;

            // Falls der key kleiner oder gleich ist, dann müssen wir in der linken hälfte weiter schauen
            // um die linke Schranke zu finden.
            if (leftKey <= sortedList.get(mid).getValue(filter)) {
                right = mid - 1;

            } else {
                // Ansonsten schaue in der rechten Seite weiter
                left = mid + 1;

            }
        }

        return right + 1;
    }

    /**
     * SearchRight führt eine binäre Suche durch um die rechte Schranke des Intervall des Filter zu finden.
     * Dabei pendelt right in Richtung der Schranke, im letzten Schritt wird Left welches sich dann auf der Schranke
     * befindet erreicht so, dass Left einen höher als die rechte Schranke geschoben wird.
     * Danach ist die Suche beendet. Die Schranke ist dann eine Position niedriger als Left.
     *
     * @param sortedList muss für den Filter vorsortiert sein
     * @param filter Filter der das Intervall enthält
     * @return index des Elementes mit größten index der Liste für das gilt key >= element - Linke Schrank
     */
    public int searchRight(List<City> sortedList, Filter filter) {

        int left = 0;
        int right = sortedList.size() - 1;
        int mid;
        double rightKey = filter.getEnd();
        this.rightComparison = 0;

        // 1. precondition check
        // Wenn die rechte Schranke größer oder gleich als das erste Element der Liste, dann Fallen keine Objekte raus.
        if (rightKey >= sortedList.get(right).getValue(filter)) return right;

        // Falls die rechte Schranke kleiner als das kleinste Element ist, dann kein Ergebnis
        if (rightKey < sortedList.get(left).getValue(filter)) throw new IllegalArgumentException("end is to low");


        // 2. So lange bis lins kleiner oder größer right
        while (left <= right) {
            mid = (left + right) / 2;

            // 3. Zählen der Schlüsselvergleiche
            this.rightComparison++;

            // 4. Falls der key größer oder gleich ist, dann müssen wir in die rechten Hälfte weiterschauen
            // um die Schranke zu finden.
            if (rightKey >= sortedList.get(mid).getValue(filter)) {
                left = mid + 1;

            } else {
                // Ansonsten gehe in die linke Hälfte
                right = mid - 1;
            }


        }

        return left - 1;
    }

    /**
     * Gibt die Anzahl an Schlüsselvergleichen für die Methode searchLeft() zurück.
     * @return
     */
    public int getLeftComparison() {
        return leftComparison;
    }

    /**
     * Gibt die Anzahl an Schlüsselvergleichen für die Methode searchRight() zurück.
     * @return
     */
    public int getRightComparison() {
        return rightComparison;
    }


    public static void main(String[] args) {

        SimpleDatabase database = new SimpleDatabase();


        List<City> dumies = new ArrayList<City>();
        dumies.add(new City(10));
        dumies.add(new City(20));
        dumies.add(new City(30));
        dumies.add(new City(40));
        dumies.add(new City(50));
        dumies.add(new City(60));
        dumies.add(new City(70));
        dumies.add(new City(80));
        dumies.add(new City(90));
        dumies.add(new City(100));


        List<Filter> filters = Arrays.asList(
                new Filter(FilterType.PLZ, 35, 80)

        );

        List<City> result = database.find(filters, dumies);

        System.out.println(result);


    }
}
