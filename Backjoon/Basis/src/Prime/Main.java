package Prime;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final int max = 100000;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numberArr = new int[max];
        int[] operandArr = new int[max];

        int cnt = 0;
        boolean hasFound = false;

        String newLine = br.readLine();
        int n = Integer.parseInt(newLine);
        while (n != 0) {
            numberArr[cnt] = n;
            for (int i = 3; i < n; i++) {
                if (isPrime(i) && isPrime(n - i)) {
                    operandArr[cnt] = i;
                    hasFound = true;
                    break;
                }
            }

            if (!hasFound) {
                // Failed to find pair of primes
                operandArr[cnt] = -1;
            }

            cnt++;
            newLine = br.readLine();
            n = Integer.parseInt(newLine);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < cnt; i++) {
            if (operandArr[i] == -1) {
                System.out.print("Goldbach's conjecture is wrong.\n");
            } else {
                bw.write(numberArr[i] + " = " + operandArr[i] + " + " + (numberArr[i] - operandArr[i]) + "\n");
            }
        }
        bw.close();
    }


    static boolean isPrime(int n) {
        if (n == 1) return false;
        if (n == 2) return true;

        for (int i = 3; i <= n / 2; i += 2) {
            if (n % i == 0) return false;
        }

        return true;
    }
}

//    static Integer[] findPrimesSmaller(int n) {
//        ArrayList<Integer> primeArr = new ArrayList<>();
//        for (int i = 3; i < n; i++) {
//            if (isPrime(i)) {
//                primeArr.add(i);
//            }
//        }
//        return primeArr.toArray(new Integer[0]);
//    }

//            for (int i = 0; i < primeArr.length - 1; i++) {
//                if (hasFound) break;
//                for (int j = i + 1; j < primeArr.length; j++) {
//                    if (primeArr[i] + primeArr[j] == n) {
//                        returnStrArr.add(n + " = " + primeArr[i] + " + " + primeArr[j]);
//                        hasFound = true;
//                        // If has found, stop finding.
//                        break;
//                    }
//                }
//            }

//    int cnt = 0;
// Each number
//            hasFound = false;
//            Integer[] primeArr = findPrimesSmaller(n);
