package PhoneBook;
import java.util.Arrays;

public class Solution {
    public static boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            String currStr = phone_book[i];
            String nextStr = phone_book[i+1];

            if (nextStr.startsWith(currStr)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String args[]) {
        String[] input = {"119", "97674223", "1195524421"};

        System.out.println(solution(input));

    }
}
