package database;

import database.filter.Filter;
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

    private static final String CHARSET = "ISO-8859-1";

    private static final String SYMBOL_DELIMITER = ";";
    private static final String SYMBOL_DEFAULT_VALUE = "/";
    private static final String SYMBOL_DECIMAL_ENGLISH = ",";
    private static final String SYMBOL_DECIMAL_GERMAN = ".";
    private static final String SYMBOL_SPACE = " ";
    private static final String SYMBOL_EMPTY = "";

    private static final String DEFAULT_VALUE = "0";

    public SimpleDatabase() {
    }

    public List<City> load(String path, int start, int end) throws IOException {

        ArrayList<City> list = new ArrayList<City>();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), CHARSET));

        int index = 1;
        String line;

        while ((line = reader.readLine()) != null) {
            if(index < start) { index++; continue; }
            if(index++ > end) { break; }

            String tokens[] = line.split(SYMBOL_DELIMITER);

            for(int i = 1; i < tokens.length; i++){
                tokens[i] = this.replaceSpace(tokens[i]);
            }

            list.add(new City(
                    tokens[0],
                    this.parseIntValue(tokens[1]),
                    this.parseDoubleValue(tokens[2]),
                    this.parseIntValue(tokens[3]),
                    this.parseIntValue(tokens[4]),
                    this.parseIntValue(tokens[5])
            ));

        }

        reader.close();

        return list;
    }

    private Double parseDoubleValue(String string){
        return Double.valueOf(string.replace(SYMBOL_DECIMAL_ENGLISH, SYMBOL_DECIMAL_GERMAN));
    }

    private Integer parseIntValue(String string){
        return Integer.valueOf(string);
    }

    private String replaceSpace(String string){
        return string.replace(SYMBOL_SPACE, SYMBOL_EMPTY).replace(SYMBOL_DEFAULT_VALUE, DEFAULT_VALUE);
    }


    public List<City> find(List<Filter> filterList, List<City> database) {

        // 1. Ergebnisliste = alles
        List<City> result = database;


        // 5. So lange 2 - 4 bis alle Filter abgearbeitet sind
        for (int i = 0; i < filterList.size(); i++) {

            // 2. Für aktuellen Filter Hilfsliste aus Ergebnisliste erstellen (Sortierung)
            result = new SelectionSort().sort(result, filterList.get(i).getFilterType());

            // 3. Binäre Suche nach Wert von links nach rechts Intervall
            int leftIndex = new BinarySearch().searchLeft(result, filterList.get(i).getFilterType(), filterList.get(i).getStart(), true);
            int rightIndex = new BinarySearch().searchRight(result, filterList.get(i).getFilterType(), filterList.get(i).getEnd(), false);

            // 4. Ergebnisliste aktualisieren mit Hilfe der Hilfsliste
            // Bsp: new Filter(FilterType.PLZ, 101, 120) leftIndex 1 = 200 bis rightIndex 0 = 100, dann leere liste
            // weil subList 1,1 leer, weil das zweite 1 exlusiv bedeutet
            result = result.subList(leftIndex, rightIndex + 1);

        }

        return result;
    }


    public static void main(String[] args) throws IOException {

        SimpleDatabase database = new SimpleDatabase();

        List<City> cities = database.load("database/StaedteStatistik.CSV", 15, 30);

        List<Filter> filters = Arrays.asList(
                //new Filter(FilterType.PLZ, 58675, 59968) // TODO - 552670, 55435
                //new Filter(FilterType.POPULATION, 0, 0)
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
