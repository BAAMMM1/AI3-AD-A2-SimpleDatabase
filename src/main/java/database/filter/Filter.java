package database.filter;

public class Filter {

    private FilterType filterType;
    private double start;
    private double end;

    /**
     * Erstellt einen Filter.
     *
     * @param columne Spaltenbezeichnung auf der er angewendet werden soll
     * @param start 0 <= start <= end
     * @param end   0 <= start <= end
     */
    public Filter(FilterType columne, double start, double end){

        if(!(0 <= start) || !(start <= end)) throw new IllegalArgumentException("0 <= start <= end");

        this.filterType = columne;
        this.start = start;
        this.end = end;

    }

    public FilterType getFilterType() {
        return filterType;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }
}
