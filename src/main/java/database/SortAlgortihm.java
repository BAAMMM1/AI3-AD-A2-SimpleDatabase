package database;

import database.filter.FilterType;

import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 26.05.2018
 */
public class SortAlgortihm {


    public List<City> sort(List<City> cities, FilterType filter){

        City min = null;

        for(int i = 0; i < cities.size(); i++){

            min = findMinStartingAt(cities, i, filter);

            swap(cities, cities.get(i), min);

        }

        return cities;

    }


    private City findMinStartingAt(List<City> cities, int i, FilterType filter) {

        City min = cities.get(i);

        for(int j = i + 1; j < cities.size(); j++){

            if(cities.get(j).compareTo(min, filter) < 0){
                min = cities.get(j);
            }

        }

        return min;
    }

    private void swap(List<City> cities, City ci, City min) {

        int i = cities.indexOf(ci);
        int m = cities.indexOf(min);

        cities.add(i, min);
        cities.remove(i + 1);

        cities.add(m, ci);
        cities.remove(m + 1);

    }

    public static void main(String[] args) throws IOException {


        SimpleDatabase database = new SimpleDatabase("database/StaedteStatistik.CSV");

        List<City> cities = database.load(0,0);

        SortAlgortihm algortihm2 = new SortAlgortihm();

        System.out.println(cities.size());

        System.out.println(algortihm2.sort(cities, FilterType.PLZ));


    }


}
