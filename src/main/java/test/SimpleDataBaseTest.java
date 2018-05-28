package test;

import database.City;
import database.SimpleDatabase;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;

/**
 * @author Shadai on 28.05.2018
 */
public class SimpleDataBaseTest {



    public void testTest(){

        SimpleDatabase db = new SimpleDatabase();

        List<City> cities = db.load("database/StaedteStatistik.CSV",1,2058);

        System.out.println(cities);

    }

    public static void main(String[] args) {
        SimpleDataBaseTest test = new SimpleDataBaseTest();
        test.testTest();
    }

}
