package database.performance;

import database.enity.City;
import database.SimpleDatabase;
import database.filter.Filter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Chris on 27.05.2018
 */
public class SimpleDatabaseTest {


    public static final String DB_CSV = "database/StaedteStatistik.CSV";

    private List<City> empty;
    private List<City> first;
    private List<City> last;
    private List<City> range;
    private List<City> all;
    private List<City> moreThanAll;

    private City firstCity;
    private City rangeStartCity;
    private City rangeEndCity;
    private City lastCity;

    private SimpleDatabase db;


    @Before
    public void init(){

        this.db = new SimpleDatabase();

        this.empty = new ArrayList<City>();
        this.first = db.load(DB_CSV,1,1);
        this.last =db.load(DB_CSV, 2058, 2058);
        this.range = db.load(DB_CSV,100,199);
        this.all = db.load(DB_CSV,1,2058);
        this.moreThanAll = db.load(DB_CSV,1,4116);

        this.firstCity = new City("Berlin, Stadt", 10178, 891.12, 3574830, 1755700, 1819130);
        this.rangeStartCity = new City("Worms, Stadt", 67547, 108.73, 82595, 40542, 42053);
        this.rangeEndCity = new City("Rheda-Wiedenbrück, Stadt", 33378, 86.72, 48526, 24273, 24253);
        this.lastCity = new City("Arnis, Stadt", 24399, 0.45, 275, 143, 132);

    }

    /*
    load - Test
     */

    @Test
    public void loadTest(){

        assertEquals("load fist city of CSV",1, this.first.size());
        assertEquals(firstCity, this.first.get(0));

        assertEquals("load last city of CSV", 1, this.last.size());
        assertEquals(lastCity, this.last.get(0));

        assertEquals("load all city of CSV", 2058, this.all.size());
        assertEquals(firstCity, this.all.get(0));
        assertEquals(lastCity, this.all.get(all.size()-1));

        assertEquals("load range of CSV", 100, this.range.size());
        assertEquals(rangeStartCity, this.range.get(0));
        assertEquals(rangeEndCity, this.range.get(range.size()-1));

        assertEquals("load more City than the CSV contains", 2058, this.moreThanAll.size());
        assertEquals(firstCity, this.moreThanAll.get(0));
        assertEquals(lastCity, this.moreThanAll.get(moreThanAll.size()-1));


    }

    @Test
    public void loadPathIncorrect(){
        assertEquals(0, db.load("/incorrectPath/", 100, 200).size());
    }

    @Test(expected = NullPointerException.class)
    public void loadPathNull(){
        db.load(null, 100, 200);
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadLowerOne(){
        db.load(DB_CSV, 0, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadNegativRangeStart(){
        db.load(DB_CSV, -10, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadRangeStartSmallerRangeEnd(){
        db.load(DB_CSV, 100, 80);
    }



    /*
    find - Test
     */
    @Test(expected = NullPointerException.class)
    public void findFilterNull() {
        db.find(null, this.all);

    }

    @Test(expected = NullPointerException.class)
    public void findDatabaseNull() {
        db.find(new ArrayList<Filter>(), null);

    }

}