package database;

import database.filter.FilterType;

import java.util.List;

/**
 * @author Shadai on 27.05.2018
 */
public class Quicksort {
    private FilterType filter;
    private List<City> sortedList;

    public List<City> sort(List<City> cities, FilterType filter) {

        //Initialize
        this.filter = filter;
        sortedList = cities;

        //1. Sortiere Liste
        sortWithQuickSort(sortedList);


        return sortedList;
    }

    private void sortWithQuickSort(List<City> toSortList) {
        //2. Wie sortWithQuickSort ich? in dem ich quick sort von index 0 bis Ende anwende
        qSort(toSortList, 0, toSortList.size() - 1);
    }

    private void qSort(List<City> cities, int left, int right) {
        if (left < right) {
            int i = gettingFixedIndex(cities, left, right); //um festzustellen, ob es einen kleineren Part gibt, der erst sortiert werden muss
            //qSort nun linken Teil
            qSort(sortedList, left, i - 1);
            //qSort dann rechten Teil
            qSort(sortedList, i + 1, right);
        }

    }


    //statt mit werten hinter indexen -> hier statt Arralist<Intger> i[] -> i[4] = irgendein Int wert zu arbeiten
    // muss nun mit Arralist<City> cities -> City[] cities

    //cities[index].getValue(filterType) um an die werte hinter z.b. PLZ zu kommen

    private int gettingFixedIndex(List<City> cities, int left, int right) {
        // 1. Pivot setzen, hier 1. Element
        City pivot = cities.get(right);
        City leftCity = cities.get(left);
        City rightCity = cities.get(right - 1);
        while (cities.indexOf(leftCity) <= cities.indexOf(rightCity)) {
            //if (leftCity.compareTo(rightCity, filter) == 1) {
            if (leftCity.getValue(filter) > pivot.getValue(filter)) {
                //tausche cities left und right
                System.out.println("rightcityIndex vor swap = "+cities.indexOf(rightCity));
                System.out.println("leftcityIndex vor swap = "+cities.indexOf(leftCity)+"\n");
                swap(cities, leftCity, rightCity);

                System.out.println("rightcityIndex = "+cities.indexOf(rightCity));
                System.out.println("leftcityIndex = "+cities.indexOf(leftCity)+"\n---------------------------\n");
                rightCity = cities.get(cities.indexOf(rightCity) - 1);

            } else leftCity = cities.get(cities.indexOf(leftCity) + 1);
        }
        swap(cities, leftCity, cities.get(right));


        return cities.indexOf(leftCity);

    }

    private void swap(List<City> cities, City ci, City min) {

        int i = cities.indexOf(ci);
        int m = cities.indexOf(min);

        cities.add(i, min);
        cities.remove(i + 1);

        cities.add(m, ci);
        cities.remove(m + 1);

    }

}
