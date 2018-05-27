package database.searching;

import database.City;
import database.SimpleDatabase;
import database.filter.Filter;
import database.filter.FilterType;
import database.sorting.SelectionSort;

import java.io.IOException;
import java.util.List;

/**
 * Diese Klasse stellt den Binäre-Suche Algorithmus da.
 *
 * @author Chris on 26.05.2018
 */
public class BinarySearch {


    public int searchLeft(List<City> sortedList, Filter filter) {

        int left = 0;
        int right = sortedList.size() - 1;
        int mid;
        double leftKey = filter.getStart();
        int counterCompare = 0;

        // 1. precondition check
        // Wenn die linke Schranke kleiner oder gleich als das erste Element der vorsortieren Liste, dann Fallen keine
        // Objekte raus.
        if (leftKey <= sortedList.get(left).getValue(filter)) return left;

        // Falls die linke Schranke größer als das größe Element ist
        if (leftKey > sortedList.get(right).getValue(filter)) throw new IllegalArgumentException("start is to big");


        while (left <= right) {
            mid = (left + right) / 2;

            /*
            if (leftKey <= sortedList.get(mid).getValue(filter)) {
                right = mid - 1;
            } else if (leftKey > sortedList.get(mid).getValue(filter)) { // Vergleiche = Kosten reduzieren

                left = mid + 1;

            }
            */
            counterCompare++;
            if (leftKey <= sortedList.get(mid).getValue(filter)) {
                right = mid - 1;

            } else {
                left = mid + 1;

            }
        }

        System.out.println("comparison left: " + counterCompare);

        return right + 1;
    }

    public int searchRight(List<City> sortedList, Filter filter) {

        int left = 0;
        int right = sortedList.size() - 1;
        int mid;
        double rightKey = filter.getEnd();
        int counterCompare = 0;

        // 1. precondition check
        // Wenn die rechte Schranke größer oder gleich als das erste Element der vorsortieren Liste, dann Fallen keine
        // Objekte raus.
        if (rightKey >= sortedList.get(right).getValue(filter)) return right;

        // Falls die rechte Schranke kleiner als das kleinste Element ist
        if (rightKey < sortedList.get(left).getValue(filter)) throw new IllegalArgumentException("end is to low");


        while (left <= right) {
            mid = (left + right) / 2;

            /*
            if (rightKey < sortedList.get(mid).getValue(filter)) {
                right = mid - 1;

            } else if (rightKey >= sortedList.get(mid).getValue(filter)) {
                left = mid + 1;

            }
            */
            counterCompare++;
            if (rightKey >= sortedList.get(mid).getValue(filter)) {
                left = mid + 1;

            } else {
                right = mid - 1;
            }


        }

        System.out.println("comparison right: " + counterCompare);

        return left - 1;
    }

}
