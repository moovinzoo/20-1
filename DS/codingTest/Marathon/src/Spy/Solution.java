package Spy;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    public static int solution(String[][] clothes) {
        HashMap<String, Integer> clothesSort = new HashMap<>();

        // Increment 1 by sort
        for(String[] item : clothes) {
            String key = item[1];
            clothesSort.put(key, clothesSort.getOrDefault(key, 0) + 1);
        }

        // If map is empty, then, there's no way to ware.
        if (clothesSort.size() == 0) return 0;

        int returnValue = 1;

        // Multiply including the case ; NOT TO WARE THAT SORT OF CLOTHES.
        Iterator<Map.Entry<String, Integer>> it = clothesSort.entrySet().iterator();
        while(it.hasNext()) {
            returnValue *= it.next().getValue() + 1;
        }

        // - 1 for NO WARE case.
        return (returnValue - 1);

    }

    public static void main(String args[]) {
//        String[][] input = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        String[][] input = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};

        System.out.println(solution(input));
    }
}
