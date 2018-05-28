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

    private int leftComparison;
    private int rightComparison;


    public int searchLeft(List<City> sortedList, Filter filter) {

        int left = 0;
        int right = sortedList.size() - 1;
        int mid;
        double leftKey = filter.getStart();
        this.leftComparison = 0;

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
            this.leftComparison++;
            if (leftKey <= sortedList.get(mid).getValue(filter)) {
                right = mid - 1;

            } else {
                left = mid + 1;

            }
        }

        return right + 1;
    }

    /**
     * Wir kommen immer in den log2 (n) fall, weil binary search immer bis zum ende läuft, also ne 2^n - 1
     * @param sortedList
     * @param filter
     * @return
     */
    public int searchRight(List<City> sortedList, Filter filter) {

        int left = 0;
        int right = sortedList.size() - 1;
        int mid;
        double rightKey = filter.getEnd();
        this.rightComparison = 0;

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
            this.rightComparison++;
            if (rightKey >= sortedList.get(mid).getValue(filter)) {
                left = mid + 1;

            } else {
                right = mid - 1;
            }


        }

        return left - 1;
    }

    public int getLeftComparison() {
        return leftComparison;
    }

    public int getRightComparison() {
        return rightComparison;
    }
}
