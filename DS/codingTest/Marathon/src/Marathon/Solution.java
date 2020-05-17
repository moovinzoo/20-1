package Marathon;

import java.util.HashMap;

public class Solution {
    public static String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> marathonMap = new HashMap<>();

        // Put all participants, if there already exists who has same name, increase.
        for (String person : participant) {
            marathonMap.put(person, marathonMap.getOrDefault(person, 0) + 1);
        }

        // Get all participants, if value > 1, decrease. else, remove element.
        for (String person : completion) {
            if (marathonMap.get(person) == 1) marathonMap.remove(person);
            else marathonMap.put(person, marathonMap.get(person) - 1);
        }

        // Single person should have remained.
        for (String key : marathonMap.keySet()) {
            return key;
        }

        // else, return empty String
        return "";
    }

    public static void main (String args[]) {
        // [leo, kiki, eden]	[eden, kiki]
        String[] participants = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};
        System.out.println(solution(participants, completion));
    }
}
