package database.performance;

import database.SimpleDatabase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Chris on 27.05.2018
 */
public class PerformanceTest {

    @Test
    public void searchSeries() {
        SimpleDatabase database = new SimpleDatabase();

        assertEquals(10, database.load("database/StaedteStatistik.CSV", 1, 10).size());
    }

}