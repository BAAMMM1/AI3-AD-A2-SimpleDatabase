package database;

import database.filter.Filter;
import database.filter.FilterType;
import database.searching.BinarySearch;
import database.sorting.SelectionSort;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chris on 08.05.2018
 */
public class SimpleDatabase {

    public static final int COLUMNE_COUNT = 6;
    private String path;

    public SimpleDatabase(String path) throws FileNotFoundException {

        this.path = path;

    }

    public List<City> load(int start, int end) throws IOException {

        ArrayList<City> list = new ArrayList<City>();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), "ISO-8859-1"));

        String line;

        // TODO - RegEx Vorverarbeitung für line

        while ((line = reader.readLine()) != null) {
            String tokens[] = line.replace("/", "-1").split(";");

            list.add(new City(
                    tokens[0],
                    Integer.valueOf(tokens[1].replace(" ", "")),
                    Double.valueOf(tokens[2].replace(" ", "").replace(",", ".")),
                    Integer.valueOf(tokens[3].replace(" ", "")),
                    Integer.valueOf(tokens[4].replace(" ", "")),
                    Integer.valueOf(tokens[5].replace(" ", ""))
            ));

        }

        //System.out.println(list);


        return list;
    }


    public List<City> find(List<Filter> filterList, List<City> database) {

        // 1. Ergebnisliste = alles
        List<City> result = database;


        // 5. So lange 2 - 4 bis alle Filter abgearbeitet sind
        for (int i = 0; i < filterList.size(); i++) {

            // 2. Für aktuellen Filter Hilfsliste aus Ergebnisliste erstellen (Sortierung)
            List<City> helpList = new SelectionSort().sort(result, filterList.get(i).getFilterType());

            // 3. Binäre Suche nach Wert von links nach rechts Intervall
            int leftIndex = new BinarySearch().searchLeft(helpList, filterList.get(i).getFilterType(), filterList.get(i).getStart(), true);
            int rightIndex = new BinarySearch().searchRight(helpList, filterList.get(i).getFilterType(), filterList.get(i).getEnd(), false);

            System.out.println("left:" + leftIndex);
            System.out.println("right:" + rightIndex);

            // 4. Ergebnisliste aktualisieren mit Hilfe der Hilfsliste
            // Bsp: new Filter(FilterType.PLZ, 101, 120) leftIndex 1 = 200 bis rightIndex 0 = 100, dann leere liste
            // weil subList 1,1 leer, weil das zweite 1 exlusiv bedeutet
            result = helpList.subList(leftIndex, rightIndex + 1);

        }

        return result;
    }


    public static void main(String[] args) throws IOException {

        SimpleDatabase database = new SimpleDatabase("database/StaedteStatistik.CSV");

        List<City> cities = database.load(0, 0);

        List<Filter> filters = Arrays.asList(
                //new Filter(FilterType.PLZ, 58675, 58675) // TODO - 552670, 55435
                new Filter(FilterType.POPULATION, -10, 480)
                //new Filter(FilterType.AREA, 13, 20)

        );

        List<City> result = database.find(filters, cities);

        System.out.println(result);

        /*
        //List<City> result = database.find(filters, cities);

        //System.out.println(result);

        List<City> dumies = new ArrayList<City>();
        dumies.add(new City(10));
        dumies.add(new City(20));
        dumies.add(new City(40));
        dumies.add(new City(50));
        dumies.add(new City(70));
        dumies.add(new City(80));


        filters = Arrays.asList(
                new Filter(FilterType.PLZ, 49, 80)

        );

        List<City> result = database.find(filters, dumies);

        System.out.println(result);
        */


    }
}
