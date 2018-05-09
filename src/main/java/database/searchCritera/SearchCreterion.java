package database.searchCritera;

public class SearchCreterion {

    private ColumneName columneName;
    private int start;
    private int end;

    public SearchCreterion(ColumneName columne, int start, int end){

        this.columneName = columne;
        this.start = start;
        this.end = end;

    }

    public SearchCreterion(ColumneName columne, int value){
        this(columne, value, value);

    }

    public ColumneName getColumneName() {
        return columneName;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
