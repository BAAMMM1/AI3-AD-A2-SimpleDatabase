package database.filter;

/**
 * Diese Klasse stellt ein Suchkriterium da. Dabei besteht der Filter aus einem Spaltenbezeichner und einem Intervall
 * von Start bis End. Der Filter dient dazu City-Objekte einer Liste einzugrenzen.
 */
public class Filter {

    private FilterType filterType;
    private double start;
    private double end;

    /**
     * Erstellt einen Filter für eine Spaltenbezeichnung und einem Intervall von Start bis Ende.
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

    @Override
    public String toString() {
        return "Filter{" +
                "filterType=" + filterType +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
