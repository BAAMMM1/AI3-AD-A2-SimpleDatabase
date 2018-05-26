package database.searching;

import database.City;
import database.SimpleDatabase;
import database.filter.FilterType;
import database.sorting.SelectionSort;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 26.05.2018
 */
public class BinarySearch {

    public int search(List<City> sortedList, FilterType filterType, int interval, boolean fromLeft) {

        // TODO - Da die Liste sortiert ist, gucken ob das Interval größer als der größte Wert ist

        int left = 0;
        int right = sortedList.size() - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (interval < sortedList.get(mid).getValue(filterType)) {
                right = mid - 1;

            } else if (interval > sortedList.get(mid).getValue(filterType)) {
                left = mid + 1;

            } else if (interval == sortedList.get(mid).getValue(filterType)) {
                return mid;

            }
        }

        // Prüfen obs das Ergebnis für die linke oder recht Schranke sein soll
        // Wenn er nicht genau das Element findet im letzten Fall, dann läuft er hier raus
        // entscheiden ob man die davor oder danach zurück gibt fürs Intervall

        return fromLeft ? left : right;

    }



    public static void main(String[] args) throws IOException {

        SimpleDatabase database = new SimpleDatabase("database/StaedteStatistik.CSV");

        List<City> cities = database.load(0, 0);

        SelectionSort algortihm2 = new SelectionSort();

        cities = algortihm2.sort(cities, FilterType.PLZ);

        BinarySearch algortihm = new BinarySearch();

        System.out.println(algortihm.search(cities, FilterType.PLZ, 55277, true));

        System.out.println(cities.subList(algortihm.search(cities, FilterType.PLZ, 55276, true), algortihm.search(cities, FilterType.PLZ, 55430, true) + 1));

    }
}