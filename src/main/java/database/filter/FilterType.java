package database.filter;

public enum FilterType {

    PLZ("Postleitzahl"),
    AREA("Fläche"),
    POPULATION("BevGesamt"),
    POPULATION_MALE("BevM"),
    POPULATION_FEMALE("BevW");

    private final String value;

    FilterType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}