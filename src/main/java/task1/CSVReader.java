package task1;

public interface CSVReader {

    List<Row> load(int start, int end);
    List<Row> find(List<SearchCritera> searchCritera);

}
