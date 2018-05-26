package database;

import database.filter.ColumneName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shadai on 26.05.2018
 */
public class Quicksort {
    public List<City> sort(List<City> cities, ColumneName filter) {
        ArrayList<City> result = new ArrayList<City>();
        ArrayList<Integer> lockedList = new ArrayList<Integer>();

        if (filter == ColumneName.PLZ) {
            int length = result.size();
                // 1. pivot bilden -> Median fällt weg, weil wir hier Liste sortieren wollen.
                // daher einfach ersten wert als pivot
                int pivot = result.get(0).getPostleitzahl();
                int left = result.get(1).getPostleitzahl();
                int rigth = result.size()+1;

                //2.Left index setzen
                //  Left geht so lange mit dem Index hoch, bis der wert, der sich hinter dem index befindet,
                //  größer dem pivot
                for (int i = 0; i < length; i++){
                    while (result.get(i).getPostleitzahl()<pivot && i < rigth){

                    }
                }

                //3.Rechts index setzen
                //  Rechts geht so lange mit dem Index runter, bis der Wert kleiner ist als der pivot



//            for (int i = 0; i < length; i++){
//
//            }


            result.get(1).getPostleitzahl();
        }
        return result;

    }

}