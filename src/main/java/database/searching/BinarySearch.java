package database.searching;

import database.City;
import database.SimpleDatabase;
import database.filter.Filter;
import database.filter.FilterType;
import database.sorting.SelectionSort;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 26.05.2018
 */
public class BinarySearch {


    public int searchLeft(List<City> sortedList, Filter filter, int interval) {

        // TODO - Da die Liste sortiert ist, gucken ob das Interval größer als der größte Wert ist

        int left = 0;
        int right = sortedList.size() - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (interval <= sortedList.get(mid).getValue(filter)) {
                right = mid - 1;

            } else if (interval > sortedList.get(mid).getValue(filter)) {
                left = mid + 1;

            }
        }

        return right + 1;
    }

    public int searchRight(List<City> sortedList, Filter filter, int interval) {

        // TODO - Da die Liste sortiert ist, gucken ob das Interval größer als der größte Wert ist

        int left = 0;
        int right = sortedList.size() - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (interval < sortedList.get(mid).getValue(filter)) {
                right = mid - 1;

            } else if (interval >= sortedList.get(mid).getValue(filter)) {
                left = mid + 1;

            }

        }

        return left - 1;
    }

}
