package database.filter;

public class Filter {

    private FilterType filterType;
    private int start;
    private int end;

    public Filter(FilterType columne, int start, int end){

        // TODO - Start darf nicht größer End sein.
        // TODO start > 0 sein

        this.filterType = columne;
        this.start = start;
        this.end = end;

    }

    public Filter(FilterType columne, int value){
        this(columne, value, value);

    }

    public FilterType getFilterType() {
        return filterType;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
