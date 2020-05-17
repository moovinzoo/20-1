package GCDLCM;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // number of test cases
        String inputStr = br.readLine();
        int cases = Integer.parseInt(inputStr);
        long[] gcdArr = new long[cases];
        int cnt = 0;

        // for each cases
        for (int i = 0; i < cases; i++) {
            // read line and store numbers in string array.
            inputStr = br.readLine();
            String[] inputs = inputStr.split(" ");
            int cntNumber = Integer.parseInt(inputs[0]);
            long sum = 0;

            // for each numbers
            for (int j = 1; j < cntNumber; j++) {
                for (int k = j + 1; k < (cntNumber + 1); k++) {
                    int first = Integer.parseInt(inputs[j]);
                    int second = Integer.parseInt(inputs[k]);
//                    gcdArray[cnt++] = getGCD(first, second);
                    sum += getGCD(first, second);
                }
            }

            gcdArr[cnt++] = sum;
        }
        
        for (int i = 0; i < gcdArr.length; i++) {
            System.out.println(gcdArr[i]);
        }
    }

    static int getGCD(int _a, int _b) {
        int gcd = 1;

        for (int i = 2; i <= _a && i <= _b; i++) {
            if (((_a % i) == 0) && ((_b % i) == 0)) {
                gcd *= i;
                _a /= i;
                _b /= i;
                i = 1; // to be 2 in next-step
            }
        }

        return gcd;
    }

    static int getLCM(int a, int b) {
        int bigger = (a >= b)? a : b;
        int smaller = (a < b)? a : b;

        if (bigger % smaller == 0) {
            return bigger;
        } else {
            int gcd = getGCD(bigger, smaller);
            bigger /= gcd;
            smaller /= gcd;
            return (bigger * smaller * gcd);
        }
    }
}
