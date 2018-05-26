package database;

import database.filter.FilterType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris on 26.05.2018
 */
public class SearchAlgortihm {

    public int search(List<City> list, FilterType filterType, int intervall) {

        int result = this.binarySearchLeftInterval(list, filterType, intervall);

        return result;

    }

    public int binarySearchLeftInterval(List<City> sortedList, FilterType filterType, int interval) {

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
                System.out.println("-> 0");
                return mid;

            }

        }

        // Wenn er nicht genau das Element findet im letzten Fall, dann läuft er hier raus
        // entscheiden ob man die davor oder danach zurück gibt fürs Intervall


        // https://stackoverflow.com/questions/16219998/modify-binary-search-to-find-the-next-bigger-item-than-the-key?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa

        return right + 1;

    }


    public int binarySearchRightInterval(List<City> sortedList, FilterType filterType, int interval) {

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
                System.out.println("-> 0: " + mid);
                return mid;

            }

        }


        // https://stackoverflow.com/questions/16219998/modify-binary-search-to-find-the-next-bigger-item-than-the-key?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa

        return right;

    }

    public static void main(String[] args) throws IOException {

        SimpleDatabase database = new SimpleDatabase("database/StaedteStatistik.CSV");

        List<City> cities = database.load(0, 0);

        SortAlgortihm algortihm2 = new SortAlgortihm();

        cities = algortihm2.sort(cities, FilterType.PLZ);

        SearchAlgortihm algortihm = new SearchAlgortihm();

        System.out.println(algortihm.binarySearchLeftInterval(cities, FilterType.PLZ, 55277));

        System.out.println(cities.subList(algortihm.binarySearchLeftInterval(cities, FilterType.PLZ, 55276), algortihm.binarySearchLeftInterval(cities, FilterType.PLZ, 55430) + 1));

    }
}
