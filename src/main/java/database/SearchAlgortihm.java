package database;

import database.filter.FilterType;

import java.util.List;

/**
 * @author Chris on 26.05.2018
 */
public class SearchAlgortihm {

    public int search(List<City> list, FilterType filterType, int start) {

        return 0;

    }

    public int binarySearch2(List<Integer> sortedList, Integer key){

        int left = 0; int right = sortedList.size() - 1, mid, result = -1;


        while (left <= right){

            mid = (left + right) /2;

            System.out.println(sortedList.get(mid));

            if(key < sortedList.get(mid)){

                System.out.println("- 1");

                right = mid - 1;

            } else if(key > sortedList.get(mid)){

                System.out.println("- 2");

                left = mid + 1;

            } else if( key.equals(sortedList.get(mid))){
                result = mid;
                break;
            }

        }

        return result;





    }
}
