package database;

import database.filter.FilterType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shadai on 26.05.2018
 */
public class Quicksort {
    private ArrayList<City> result = new ArrayList<City>();
    private City pivot;
    private City left;
    private City right;


    public List<City> sort(List<City> cities, FilterType filter) {
        ArrayList<City> result = new ArrayList<City>();
        ArrayList<Integer> lockedList = new ArrayList<Integer>();

        int length = result.size();

        do {
            //10.   Bestimme Bereich, der geprüft werden muss


            if (lockedList.size()==0) {
                // 1. Initialisierung mit ersten Werten
                // pivot bilden -> Median fällt weg, weil wir hier Liste sortieren wollen.
                // daher einfach ersten wert als pivot
                pivot = result.get(0);
                left = result.get(1);
                right = result.get(result.size() - 1);
            }
            else{
                //10a Nimm erstes Element aus locked list, welches
                //nicht an seine richtigen Position ist
                int i = 0;

                while (result.indexOf(result.get(i))==lockedList.indexOf(i)){i++;}
                pivot = result.get(i);
                left = result.get(i+1);

                i = i+2;
                while (!(result.indexOf(result.get(i))==lockedList.indexOf(i))&&i<result.size()){i++;}
                right = result.get(i);  // bis nächste fixierung oder bis Ende
            }

            //5. Mach das so lange, bis left größer right ist

            do {
                //2.Left index setzen
                //  Left geht so lange mit dem Index hoch, bis der wert, der sich hinter dem index befindet,
                //  größer dem pivot
                while (left.compareTo(pivot, filter) == -1) {
                    left = result.get(result.indexOf(left) + 1);
                }

                //3.Rechts index setzen
                //  Rechts geht so lange mit dem Index runter, bis der Wert kleiner ist als der pivot
                while (right.compareTo(pivot, filter) == 1) {
                    right = result.get(result.indexOf(right) + 1);
                }

                //4. Tausche left mit right
                swap(result, left, right);
            }
            while (result.indexOf(left) < result.indexOf(right)); //5. So lange bis Left und Right vertauscht sind (Right - Left)

            //6. Right Position ist die fixe Position von pivot
            swap(result, right, pivot);

            //7. Fixierte Position in Liste zwischenspeichern
            lockedList.add(result.indexOf(pivot));

            //8. Prüfe, ob ungeprüfte Bereiche nun alleine stehen, falls ja, setz sie auf locked
            proof(lockedList);
        } while (!(result.size()==lockedList.size()));  //9. wiederhole den Schritt, bis alle in der Liste locked sind



        return result;

    }

    private void proof(ArrayList<Integer> lockedList) {
        //hinzugefügte Zahl ist immer an letzter Position, diese nun
        //hier an richtige Position bringen
        if (lockedList.size()>1){
            for (int i = 0;i <lockedList.size(); i++){
               if (lockedList.get(lockedList.size()-1)<i){
                   lockedList.add(i,lockedList.size()-1);
                   lockedList.remove(lockedList.size()-1);
                }
            }
        }
        //Wenn Liste nur 1 oder 2 Elemente hat, kann
        //da kein Element dazwischen gefüllt werden
        if (!(lockedList.size()<3)) {

            // Liste muss von 0 - result length gehen,
            // falls nun [0,1,3,4], dann muss 2 ebenfalls fixiert sein

            for (int i = lockedList.get(0); i <= lockedList.size() - 2; i++) {
                // Wenn der nächste Index nach i 2 größer ist als i
                if (lockedList.get(i + 1) == lockedList.get(i) + 2) ;
                lockedList.add(lockedList.get(i) + 1);
            }

            //Falls nur noch der Letzte Index fehlt bei der Fixierung, fixiere ihn
            if (lockedList.size() == result.size() - 1) {
                lockedList.add(result.size() - 1);
            }
        }

    }

    private void swap(List<City> cities, City ci, City min) {

        int i = cities.indexOf(ci);
        int m = cities.indexOf(min);

        cities.add(i, min);
        cities.remove(i + 1);

        cities.add(m, ci);
        cities.remove(m + 1);

    }

}