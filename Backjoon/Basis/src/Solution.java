import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'maximumProfit' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY price as parameter.
     */

    enum Action {
        BUY, SELL, STAY
    }
//
//    public static long maximumProfit(List<Integer> price) {
//        // Write your code here
//        int days = price.size();
//        long profit = 0;
//
//        // If only one day, NOT to buy is maximum profit.
//        if (days == 1) return 0;
//
//        // If days > 1, before last day.
//        // // cause, next min & max decided before and only 1 day left, then do nothing is better.
//        else {
//            // Store daily actions
//            int cntSell = 0;
//            Action[] actionArr = new Action[days];
//
//            // Each day, except last day.
//            for (int i = 0; i < days - 1; i++) {
//
//                // Find next minimum day,
//                int nextMin = i;
//                for (int j = i; j < days - 1; j++) {
//                    if (price.get(j) >= price.get(j+1)) {
//                        actionArr[j] = Action.STAY;
//                        nextMin = j;
//                    }
//                }
//
//                // If price keep decreasing till end.
//                if (nextMin == days - 2 && price.get(nextMin) >= price.get(nextMin + 1))
//                    break; // There is no way
//
//                actionArr[nextMin] = Action.BUY;
//
////                // There is next minimum, update day.
////                i = nextMin;
//
//                // Find next maximum day, from last minimum day.
//                int nextMax = -1;
//                for (int j = nextMin + 1; j < days - 1; j++) {
//                    if (price.get(j) <= price.get(j + 1)) {
//                        actionArr[j] = Action.STAY;
//                        nextMax = j;
//                    }
//                }
//
//                if (nextMax != -1) {
//                    cntSell++;
//                    actionArr[nextMax] = Action.SELL;
//                }
//
//                i = nextMax;
//            }
//
//            // Strategy decided.
//            int cnt = 0;
//            for (int i = 0; i < days && cntSell > 0; i++) {
//                if (actionArr[i] == Action.BUY) {
//                    profit -= price.get(i);
//                } else if (actionArr[i] == Action.SELL) {
//                    profit += (price.get(i) * cnt);
//                    cnt = 0;
//                } else { // STAY
//                    cnt++;
//                }
//            }
//        }
//
//        return profit;
//    }

    static class Interval {
        int buy, sell;
    }

    public static long maximumProfit(List<Integer> price) {
//
//    } stockBuySell(int price[], int days) {
        // Prices must be given for at least two days
        int days = price.size();
        if (days == 1)
            return 0;

        int cntSell = 0;

        // solution array
        Action[] actionArr = new Action[days];

        // Traverse through given price array
        int i = 0;
        while (i < days - 1) {
            // Find Local Minima. Note that the limit is (days-2) as we are
            // comparing present element to the next element.
            while ((i < days - 1) && (price.get(i+1) <= price.get(i)))
                i++;

            // If we reached the end, break as no further solution possible
            if (i == days - 1)
                break;

            actionArr[i++] = Action.BUY;
            // Store the index of minima

            // Find Local Maxima.  Note that the limit is (days-1) as we are
            // comparing to previous element
            while ((i < days) && (price.get(i) >= price.get(i - 1)))
                i++;

            // Store the index of maxima
            actionArr[i - 1] = Action.SELL;

            // Increment number of buy/sell
            cntSell++;
        }

        // print solution
        if (cntSell == 0) return 0;
        else {
            System.out.println("BEFORE");
            for (int j = 0; j < days; j++) {
                System.out.println("Day" + j + ": " + actionArr[j]);
            }

            int dayOfMaximum = -1;
            int maximumPrice = 0;
            for (int j = 0; j < days; j++) {
                if (actionArr[j] == Action.SELL) {
                    if (maximumPrice < price.get(j)) {
                        dayOfMaximum = j;
                        maximumPrice = price.get(j);
                    }
                }
            }

            Action last = null;
            for (int j = 0; j < days && cntSell > 0; j++) {
                if (actionArr[j] == Action.BUY) {
                    last = Action.BUY;
                } else if (actionArr[j] == Action.SELL) {
                    last = Action.SELL;
                } else {
                    if (last == Action.BUY) {
                        actionArr[j] = Action.BUY;
                    }
                }
            }

//
            System.out.println("MID");
            for (int j = 0; j < days; j++) {
                System.out.println("Day" + j + ": " + actionArr[j]);
            }

            long localProfit = 0;
            int cntWeight = 0;
            for (int j = 0; j < dayOfMaximum; j++) {
                if (actionArr[j] == Action.BUY) {
                    // BUY
                    localProfit -= price.get(j);
                    cntWeight++;
                } else if (actionArr[j] == Action.SELL) {
                    // 극대값에서 안팔고 오히려 살 때
                    long left = localProfit - price.get(j) + (cntWeight + 1) * maximumPrice;

                    // 극대값에서 팔지도 않고 사지도 않을 때
                    long mid = localProfit + (cntWeight) * maximumPrice;
                    cntWeight = 0;

                    // 극대값에서 예정대로 팔 때
                    long right = localProfit + (cntWeight * price.get(j));
                    cntWeight = 0;

                    // left가 가장 클 때
                    if (left > right && left > mid) {
                        actionArr[j] = Action.BUY;
                    } else if (mid > left && mid > right) {
                        // mid가 가장 클 때
                        actionArr[j] = null;
                    }
                }
            }

            System.out.println("AFTER");
            for (int j = 0; j < days; j++) {
                System.out.println("Day" + j + ": " + actionArr[j]);
            }

            long globalProfit = 0;
            cntWeight = 0;
            for (int j = 0; j < days; j++) {
                if (actionArr[j] == Action.BUY) {
                    globalProfit -= price.get(j);
                    cntWeight++;
                } else if (actionArr[j] == Action.SELL) {
                    globalProfit += price.get(j) * cntWeight;
                    cntWeight = 0;
                }
            }

            return globalProfit;
        }
    }

//    public static long maximumProfit(List<Integer> price) {
//    public static long maximumProfit(List<Integer> price) {
////
////    } stockBuySell(int price[], int days) {
//        // Prices must be given for at least two days
//        int days = price.size();
//        if (days == 1)
//            return 0;
//
//        int cntSell = 0;
//
//        // solution array
//        Action[] actionArr = new Action[days];
//
//        // Traverse through given price array
//        int i = 0;
//        while (i < days - 1) {
//            // Find Local Minima. Note that the limit is (days-2) as we are
//            // comparing present element to the next element.
//            while ((i < days - 1) && (price.get(i+1) <= price.get(i)))
//                i++;
//
//            // If we reached the end, break as no further solution possible
//            if (i == days - 1)
//                break;
//
//            actionArr[i++] = Action.BUY;
//            // Store the index of minima
//
//            // Find Local Maxima.  Note that the limit is (days-1) as we are
//            // comparing to previous element
//            while ((i < days) && (price.get(i) >= price.get(i - 1)))
//                i++;
//
//            // Store the index of maxima
//            actionArr[i - 1] = Action.SELL;
//
//            // Increment number of buy/sell
//            cntSell++;
//        }
//
//        // print solution
//        if (cntSell == 0) return 0;
//        else {
//
//            // Handle null between buy and sell
//            Action last = null;
//            for (int j = 0; j < days && cntSell > 0; j++) {
//                if (actionArr[j] == Action.BUY) {
//                    last = Action.BUY;
//                } else if (actionArr[j] == Action.SELL) {
//                    last = Action.SELL;
//                } else {
//                    if (last == Action.BUY) {
//                        actionArr[j] = Action.BUY;
//                    }
//                }
//            }
//
//            int dayOfMaximum = -1;
//            Integer maximumPrice = 0;
//            for (int j = 0; j < days; j++) {
//                if (actionArr[j] == Action.SELL) {
//                    if (maximumPrice < price.get(j)) {
//                        dayOfMaximum = j;
//                        maximumPrice = price.get(j);
//                    }
//                }
//            }
//
//            for (int j = 0; j < dayOfMaximum; j++) {
//                if (actionArr[j] == Action.SELL) {
//                    actionArr[j] = Action.BUY;
//                }
//            }
////
////            System.out.println("AFTER");
////            for (int j = 0; j < days; j++) {
////                System.out.println("Day" + j + ": " + actionArr[j]);
////            }
//
//            long profit = 0;
//            int cntWeight = 0;
//            for (int j = 0; j < days; j++) {
//                if (actionArr[j] == Action.BUY) {
//                    // BUY
//                    profit -= price.get(j);
//                    cntWeight++;
//                } else if (actionArr[j] == Action.SELL) {
//                    profit += (price.get(j) * cntWeight);
//                    cntWeight = 0;
//                }
//            }
//
//            return profit;
//        }
//    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int units = sc.nextInt();
        for (int i = 0; i < units; i++) {
            int days = sc.nextInt();
            List<Integer> price = new ArrayList<>();
            for (int j = 0; j < days; j++) {
                price.add(sc.nextInt());
            }
            System.out.println("Unit" + i + ": " + Result.maximumProfit(price));
        }
    }
}
