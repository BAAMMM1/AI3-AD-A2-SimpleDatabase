package database;

import database.searchCritera.SearchCreterion;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chris on 08.05.2018
 */
public class SimpleDatabase implements CSVReadable {

    public static final int COLUMNE_COUNT = 6;
    private String path;

    public SimpleDatabase(String path) throws FileNotFoundException {

        this.path = path;

    }

    @Override
    public List<Row> load(int start, int end) throws IOException {

        ArrayList<Row> list = new ArrayList<Row>();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path),"ISO-8859-1"));

        String line;

        // TODO - RegEx Vorverarbeitung für line

        while((line = reader.readLine()) != null){
            String tokens[] = line.replace("/", "0").split(";");

            list.add(new Row(
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

    @Override
    public List<Row> find(List<SearchCreterion> searchCritera) {
        return null;
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
