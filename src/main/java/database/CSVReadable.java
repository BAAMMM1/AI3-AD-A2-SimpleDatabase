package database;

import database.searchCritera.SearchCreterion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CSVReadable {

    List<Row> load(int start, int end) throws IOException;
    List<Row> find(List<SearchCreterion> searchCritera);

}
