package database;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Shadai on 27.05.2018
 */
public class SimpleDatabaseTest {
    @Before
    public void init(){
        SimpleDatabase database = new SimpleDatabase();

        List<City> testCitiesLoaded = database.load("StaedteStatistik.CSV", 1, 10);
    }

    @Test
    public void load() {
        SimpleDatabase loadDatabase = new SimpleDatabase();

        List<City> testLoad = loadDatabase.load("StaedteStatistik.CSV",1,10);

        assertEquals("number of items loaded should be equal numbers loaded",10,testLoad.size());
        assertEquals("1st item check of load", "", testLoad.get(0));

    }

    @Test
    public void searchSeries() {
        SimpleDatabase database = new SimpleDatabase();

        assertEquals(10, database.load("StaedteStatistik.CSV", 1, 10).size());
    }

    @Test
    public void find() {
    }
}