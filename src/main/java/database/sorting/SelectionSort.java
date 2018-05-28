package database.sorting;

import database.enity.City;
import database.SimpleDatabase;
import database.filter.Filter;
import database.filter.FilterType;

import java.io.IOException;
import java.util.List;

/**
 * Diese Klasse stellt den Selectionsort Algorithmus da. Er dient dazu City-Objekte nach einem übergebenen Filter
 * zu sortieren.
 *
 * @author Chris on 26.05.2018
 */
public class SelectionSort {


    /**
     * Führt die Handlungsvorschrift des SelectionsortAlgortihmus aus.
     *
     * @param cities zu sortierene Liste
     * @param filter Filter der angibt nach welcher
     * @return
     */
    public List<City> sort(List<City> cities, Filter filter){


        for(int i = 0; i < cities.size(); i++){

            City min = cities.get(i);

            for(int j = i + 1; j < cities.size(); j++){

                if(cities.get(j).compareTo(min, filter) < 0){
                    min = cities.get(j);
                }

            }

            swap(cities, cities.get(i), min);

        }

        return cities;

    }


    /**
     * Hilfsmethode die zwei Elemente einer Liste miteinander tauscht.
     *
     * @param cities
     * @param ci
     * @param min
     */
    private void swap(List<City> cities, City ci, City min) {

        int i = cities.indexOf(ci);
        int m = cities.indexOf(min);

        cities.add(i, min);
        cities.remove(i + 1);

        cities.add(m, ci);
        cities.remove(m + 1);

    }

    public static void main(String[] args) throws IOException {


        SimpleDatabase database = new SimpleDatabase();

        List<City> cities = database.load("database/StaedteStatistik.CSV",0,0);

        SelectionSort algortihm2 = new SelectionSort();

        System.out.println(cities.size());

        System.out.println(algortihm2.sort(cities, new Filter(FilterType.PLZ, 1,2)));


    }


}
