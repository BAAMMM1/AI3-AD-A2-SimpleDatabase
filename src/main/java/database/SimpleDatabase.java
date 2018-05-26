package database;

import database.filter.Filter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris on 08.05.2018
 */
public class SimpleDatabase{

    public static final int COLUMNE_COUNT = 6;
    private String path;

    public SimpleDatabase(String path) throws FileNotFoundException {

        this.path = path;

    }

    public List<City> load(int start, int end) throws IOException {

        ArrayList<City> list = new ArrayList<City>();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path),"ISO-8859-1"));

        String line;

        // TODO - RegEx Vorverarbeitung für line

        while((line = reader.readLine()) != null){
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

        System.out.println(list);





        return null;
    }


    public List<City> find(List<Filter> filterList, List<City> database) {

        // 1. Ergebnisliste = alles
        List<City> result = database;


        // 5. So lange 2 - 4 bis alle Filter abgearbeitet sind
        for(int i = 0; i < filterList.size(); i++){

            // 2. Für aktuellen Filter Hilfsliste aus Ergebnisliste erstellen (Sortierung)
            List<City> helpList = new SortAlgortihm().sort(result, filterList.get(i).getColumneName());

            // 3. Binäre Suche nach Wert von links nach rechts Intervall
            int leftIntervall = new SearchAlgortihm().search(filterList.get(i).getStart());
            int rightIntervall = new SearchAlgortihm().search(filterList.get(i).getEnd());

            // 4. Ergebnisliste aktualisieren mit Hilfe der Hilfsliste
            result = helpList.subList(leftIntervall, rightIntervall);


        }


        return result;
    }
















    public static void main(String[] args) {

        try {

            SimpleDatabase database = new SimpleDatabase("database/StaedteStatistik.CSV");
            database.load(0,0);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
