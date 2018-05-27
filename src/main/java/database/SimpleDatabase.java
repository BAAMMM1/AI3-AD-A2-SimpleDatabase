package database;

import database.filter.Filter;
import database.filter.FilterType;

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
            String tokens[] = line.replace("/", "0").split(";");

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
            List<City> helpList = new Quicksort().sort(result, filterList.get(i).getFilterType());

            // 3. Binäre Suche nach Wert von links nach rechts Intervall
            int leftIndex = new SearchAlgortihm().search(helpList, filterList.get(i).getFilterType(), filterList.get(i).getStart());
            int rightIndex = new SearchAlgortihm().search(helpList, filterList.get(i).getFilterType(), filterList.get(i).getEnd());

            // 4. Ergebnisliste aktualisieren mit Hilfe der Hilfsliste
            result = helpList.subList(leftIndex, rightIndex + 1);


        }


        return result;
    }


    public static void main(String[] args) throws IOException {

        SimpleDatabase database = new SimpleDatabase("database/StaedteStatistik.CSV");

        List<City> cities = database.load(0, 0);

        List<Filter> filters = Arrays.asList(
                new Filter(FilterType.PLZ, 0, 1000)// TODO - start 55275, ende 55431
//                new Filter(FilterType.POPULATION, 1901, 8388),
//                new Filter(FilterType.POPULATION_FEMALE, 3853, 5096)
        );
        List<City> dumies = new ArrayList<City>();
        dumies.add(new City(100));
        dumies.add(new City(800));
        dumies.add(new City(400));
        dumies.add(new City(700));
        dumies.add(new City(500));
        dumies.add(new City(200));
        List<City> result = database.find(filters, dumies);

        System.out.println(result);


    }
}
