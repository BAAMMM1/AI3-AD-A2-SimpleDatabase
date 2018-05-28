package database;

import database.filter.Filter;
import database.filter.FilterType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shadai on 27.05.2018
 */
public class Quicksort {
    private Filter filter;

    public List<City> sort(List<City> cities, Filter filter) {

        //Initialize
        this.filter = filter;

        //1. Sortiere Liste
        sortWithQuickSort(cities);


        return cities;
    }

    private void sortWithQuickSort(List<City> cities) {
        //2. Wie sortWithQuickSort ich? in dem ich quick sort von index 0 bis Ende anwende
        System.out.println("cities.size = "+cities.size()+" Liste die Sortiert werden soll:\n------------------------------------\n");
        cities.stream().forEach(city -> System.out.print(city.getValue(filter)+"  "));
        qSort(cities, 0, cities.size() - 1);

    }

    private void qSort(List<City> cities, int left, int right) {
        if (left < right) {
            System.out.println("für qsort übergebener left "+left+" und right "+right+"\n");
            System.out.println("cities.size = "+cities.size()+" Liste nun:\n------------------------------------\n");
            int i = partition(cities, left, right); //um festzustellen, ob es einen kleineren Part gibt, der erst sortiert werden muss
            //qSort nun linken Teil

//            for(int j = 0; j< cities.size(); j++){
//                System.out.println(cities.get(j).getValue(filter));
//            }

            qSort(cities, left, i - 1);
            System.out.println("Links durch");
            //qSort dann rechten Teil
            qSort(cities, i + 1, right);
            System.out.println("rechts durch");
        }

    }


    //statt mit werten hinter indexen -> hier statt Arralist<Intger> i[] -> i[4] = irgendein Int wert zu arbeiten
    // muss nun mit Arralist<City> cities -> City[] cities

    //cities[index].getValue(filterType) um an die werte hinter z.b. PLZ zu kommen

    private int partition(List<City> cities, int left, int right) {
        // 1. Pivot setzen, hier 1. Element
        City pivot = cities.get(right);
        City i = cities.get(left);
        City j = cities.get(right - 1);
        while (cities.indexOf(i) <= cities.indexOf(j)) {
            //if (leftCity.compareTo(rightCity, filter) == 1) {
            System.out.println("i value filter der verglichen wird "+ i.getValue(filter));
            System.out.println("pivot value filter der verglichen wird "+ pivot.getValue(filter)+"\n");
            if (i.getValue(filter) > pivot.getValue(filter)) {
                //tausche cities left und right

                System.out.println("j vor swap " + cities.indexOf(j));
                swap(cities, i, j);
                System.out.println("j nach swap, worauf -1 angewandt wird "+cities.indexOf(i)+"\n---------------\n");

                j = cities.get(cities.indexOf(i) - 1);              //keine Ahnung wie man hier Out of Bound kommen kann...

            } else {
                System.out.println("nun, i, welches erhöht wird "+cities.indexOf(i)+"\n");
                i = cities.get(cities.indexOf(i) + 1);
            }
        }
        swap(cities, i, pivot);


        return cities.indexOf(i);

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
