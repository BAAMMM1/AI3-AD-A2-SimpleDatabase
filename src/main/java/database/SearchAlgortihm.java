package database;

import database.filter.FilterType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 26.05.2018
 */
public class SearchAlgortihm {

    public int search(List<City> list, FilterType filterType, int intervall) {

        int result = this.binarySearch(list, filterType, intervall);

        return result;

    }

    public int binarySearch(List<City> sortedList, FilterType filterType, int intervall) {

        int left = 0;
        int right = sortedList.size() - 1;
        int mid = 1; // TODO - ohne Initialisierung


        while (left <= right) {

            mid = (left + right) / 2;

            if (intervall < sortedList.get(mid).getValue(filterType)) {

                right = mid - 1;

            } else if (intervall > sortedList.get(mid).getValue(filterType)) {

                left = mid + 1;

            } else if (intervall == sortedList.get(mid).getValue(filterType)) {
                return mid;

            }

        }

        // PLZ: 55421, 55422, 55424, 55430
        // Damit das intervall passt
        if(sortedList.get(mid).getValue(filterType) < intervall){
            return mid - 1;
        } else {
            return mid;
        }


    }

    public static void main(String[] args) throws IOException {

        SimpleDatabase database = new SimpleDatabase("database/StaedteStatistik.CSV");

        List<City> cities = database.load(0, 0);

        SortAlgortihm algortihm2 = new SortAlgortihm();

        cities = algortihm2.sort(cities, FilterType.PLZ);

        SearchAlgortihm algortihm = new SearchAlgortihm();

        // 55282 -> 1085  -> 55282
        // 55283 -> 1085  -> 55283
        // 55284 -> 1086  -> 55286
        // 55285 -> 1086  -> 55286
        // 55286 -> 1086  -> 55286

        System.out.println(algortihm.binarySearch(cities, FilterType.PLZ, 55283));

        System.out.println(cities.subList(algortihm.binarySearch(cities, FilterType.PLZ, 55276), algortihm.binarySearch(cities, FilterType.PLZ, 55430) + 1));

    }
}
