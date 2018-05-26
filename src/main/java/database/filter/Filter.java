package database.filter;

public class Filter {

    private ColumneName columneName;
    private int start;
    private int end;

    public Filter(ColumneName columne, int start, int end){

        this.columneName = columne;
        this.start = start;
        this.end = end;

    }

    public Filter(ColumneName columne, int value){
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
