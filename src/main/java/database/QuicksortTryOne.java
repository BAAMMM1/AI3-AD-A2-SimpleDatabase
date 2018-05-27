package database;

import database.filter.FilterType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shadai on 26.05.2018
 */
public class QuicksortTryOne {
    private ArrayList<City> result = new ArrayList<City>();
    private City pivot;
    private City left;
    private City right;


    public List<City> sort(List<City> cities, FilterType filter) {
        List<City> result = new ArrayList<City>();
        List<Integer> lockedList = new ArrayList<Integer>();


        int length = result.size();
        result=cities;

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
                System.out.println("locked list vor While: "+lockedList.toString());
                //ist das element von der locked list = dem ersten element in der result,
                //dann, und NUR dann, ist das gelocked und man muss nächsten nehmen, sprich i um 1 erhöhen
                //das muss sooft gemacht werden bis unterschiedlich, damit wir auf dem ersten feld sind
                //welches nicht gelocked ist!

                while (lockedList.get(i)==result.indexOf(result.get(lockedList.get(i)))){
                    System.out.println("lockedList.get(i) = "+lockedList.get(i));
                    System.out.println("result.indexOf(result.get(lockedList.get(i)) = " +result.indexOf(result.get(lockedList.get(i))));
                    i++;}
                //    System.out.println("Left setzen nach jedem Durchgang")
                pivot = result.get(i);
                System.out.println("pivot = "+result.indexOf(pivot));
                left = result.get(i+1);
                System.out.println("left = "+result.indexOf(left));

                i = i+2;
                while (!(result.indexOf(result.get(i))==lockedList.indexOf(i))&&i<result.size()-1){
                   // System.out.println("Right setzen nach jedem Durchgang");
                    i++;}
                right = result.get(i);  // bis nächste fixierung oder bis Ende
                System.out.println("right ="+result.indexOf(right));
            }

            //5. Mach das so lange, bis left größer right ist

            do {
                //2.Left index setzen
                //  Left geht so lange mit dem Index hoch, bis der wert, der sich hinter dem index befindet,
                //  größer dem pivot
                while (left.compareTo(pivot, filter) == -1) {
                    System.out.println("left hoch");
                    left = result.get(result.indexOf(left) + 1);
                }

                //3.Rechts index setzen
                //  Rechts geht so lange mit dem Index runter, bis der Wert kleiner ist als der pivot
                while (right.compareTo(pivot, filter) == 1) {
               //     System.out.println("right runter");
                    right = result.get(result.indexOf(right) - 1);
                }
                System.out.println("hier schleife für linker und Rechter Index verschieben und ggf swappen");
                //4. Tausche left mit right
                swap(result, left, right);
                System.out.println("//5. So lange bis Left und Right vertauscht sind (Right - Left)");
            }
            while (result.indexOf(left) < result.indexOf(right)); //5. So lange bis Left und Right vertauscht sind (Right - Left)

            //6. Right Position ist die fixe Position von pivot

            System.out.println("Vor swap: Posi of Pivot = "+ result.indexOf(pivot)+" Wert of Pivot: "+ pivot.toString());
            System.out.println("Vor swap: Posi of right = "+ result.indexOf(right)+" Wert of right: "+ right.toString()+"\n\n\n");
            swap(result, right, pivot);
            System.out.println("Nach swap: Posi of Pivot = "+ result.indexOf(pivot)+" Wert of Pivot: "+ pivot.toString());
            System.out.println("nach swap Posi of right = "+ result.indexOf(right)+" Wert of right: "+ right.toString());


            //7. Fixierte Position in Liste zwischenspeichern
            System.out.println(result.indexOf(pivot));
            lockedList.add(result.indexOf(pivot));
            System.out.println("nun proof");



            //8. Prüfe, ob ungeprüfte Bereiche nun alleine stehen, falls ja, setz sie auf locked
            proof(lockedList);
            System.out.println("9. wiederhole den Schritt, bis alle in der Liste locked sind");
        } while (!(result.size()==lockedList.size()));  //9. wiederhole den Schritt, bis alle in der Liste locked sind


        System.out.println("nun fertig");
        return result;

    }

    private void proof(List<Integer> prooflist) {
        //hinzugefügte Zahl ist immer an letzter Position, diese nun
        //hier an richtige Position bringen
        //Bsp: 0 1 3 4 2   -> 2 ist als letztes hinzugefügt worden und muss nun an richtige posi eingebraucht werden
        if (prooflist.size() > 1) {
            for (int i = 0; i < prooflist.size(); i++) {               //1. Durchlauf bei Index 0                      durchlauf 3
                if (prooflist.get(prooflist.size() - 1) <= i) {        //wenn letztes teil von locked list (2) < 0     2 < 3? ja
                    System.out.println("add remove");
                    prooflist.add(i, prooflist.get(prooflist.size() - 1));   // füge element an 0 ein                füge element an 3 ein -> 0 1 3 4 2 -> 0 1 3 2 2
                    prooflist.remove(prooflist.size() - 1);     // entferne letztes Element                     entferne letztes Element
                    break;
                }
            }
        }
        //Wenn Liste nur 1 oder 2 Elemente hat, kann
        //da kein Element dazwischen gefüllt werden
        if (!(prooflist.size() < 3)) {

            // Liste muss von 0 - result length gehen,
            // falls nun [0,1,3,4], dann muss 2 ebenfalls fixiert sein

            //nicht bis letztem, sondern 1 davor aufhören, damit man dazwischen noch was hinzufügen kann
            for (int i = prooflist.get(0); i <= prooflist.size() - 2; i++) {          //1. Durchgang: i = 0 i size = 4    wenn 1<4

                // Wenn der nächste Index nach i 2 größer ist als i
                if (prooflist.get(i + 1) == prooflist.get(i) + 2) {                   //Wenn index 1 = 0+2
                    System.out.println("add nun: "+ i+1);
                    prooflist.add(prooflist.get(i) + 1,prooflist.get(i) + 1 );//füge an posi i+1 i+1 ein
                    break;
                }

                //Falls nur noch der Letzte Index fehlt bei der Fixierung, fixiere ihn
                if (prooflist.size() == result.size() - 1) {
                    prooflist.add(result.size() - 1);
                }
            }

        }
        System.out.println("locked list = "+prooflist.toString());
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