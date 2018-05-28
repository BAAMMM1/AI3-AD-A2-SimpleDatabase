package database.performance;

import database.City;
import database.SimpleDatabase;
import database.filter.Filter;
import database.filter.FilterType;
import database.searching.BinarySearch;
import database.sorting.SelectionSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Diese Klasse stellt den Laufzeittest für eine unterschiedliche Anzahl an Objekten da.
 *
 * @author Chris on 27.05.2018
 */
public class SearchRuntime {

    /**
     * Gibt für wie viele Objekte der Test ausgeführt wird.
     */
    private static final int NUMBERS_OF_OBJECTS = 20000;

    /**
     * Diese Methode führt die Messungen für die Laufzeit des BinarySearch für die linke Schranke durch
     */
    public void searchLeftSeries(){

        System.out.println("Binary for the left side: ");

        // 1. Erstelle Cities
        List<City> cityList = this.createRandomList(NUMBERS_OF_OBJECTS);;

        SimpleDatabase db = new SimpleDatabase();
        Random random = new Random();

        // 2. Verdoppel jeweils die Eingabe von Cities
        for(int i = 1; i < cityList.size(); i = i * 2){

            List<City> cities = cityList.subList(0,i);
            new SelectionSort().sort(cities, FilterType.PLZ);
            Filter filter = new Filter(FilterType.PLZ, random.nextInt(cities.get(cities.size()-1).getPostleitzahl())+1, 99999);

            BinarySearch search = new BinarySearch();

            // 3. Führe die Suche durch
            search.searchLeft(cities, filter);

            // 4. Ergebnis ausgabe
            System.out.println("n = " + i + ": " + search.getLeftComparison());

        }

    }


    /**
     * Diese Methode führt die Messungen für die Laufzeit des BinarySearch für die rechte Schranke durch
     */
    public void searchRightSeries(){

        System.out.println("Binary for the right side: ");

        // 1. Erstelle Cities
        List<City> cityList = this.createRandomList(NUMBERS_OF_OBJECTS);;

        SimpleDatabase db = new SimpleDatabase();
        Random random = new Random();

        // 2. Verdoppel jeweils die Eingabe von Cities
        for(int i = 1; i < cityList.size(); i = i * 2){

            List<City> cities = cityList.subList(0,i);
            new SelectionSort().sort(cities, FilterType.PLZ);
            Filter filter = new Filter(FilterType.PLZ, 0, random.nextInt(cities.get(cities.size()-1).getPostleitzahl())+1);

            BinarySearch search = new BinarySearch();

            // 3. Führe die Suche durch
            search.searchRight(cities, filter);

            // 4. Ergebnis ausgabe
            System.out.println("n = " + i + ": " + search.getRightComparison());

        }

    }

    /**
     * Erstellt eine Liste mit random erstellten City für eine übergebene Anzahl.
     *
     * @param size muss 2 <= size
     * @return Liste mit random Cities, falls size nicht 2 <= size ist
     */
    private List<City> createRandomList(int size){

        if(!(2 <= size)) throw new IllegalArgumentException();

        List<City> cityList = new ArrayList<>();
        Random random = new Random();
        cityList.add(new City("City 0", 1,1,1,1,1));
        cityList.add(new City("City " + size, 99999,99999,99999,99999,99999));

        for(int i = 2; i < size; i++){

            City city = new City("City " + i,
                    random.nextInt(99998)+1,
                    random.nextInt(99998)+1,
                    random.nextInt(99998)+1,
                    random.nextInt(99998)+1,
                    random.nextInt(99998)+1
            );

            cityList.add(city);
        }

        return cityList;

    }


    public static void main(String[] args) {

        SearchRuntime searchRuntime = new SearchRuntime();

        searchRuntime.searchLeftSeries();

        System.out.println();

        searchRuntime.searchRightSeries();

    }


}
