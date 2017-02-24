package Week5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouxuexuan on 24/2/17.
 */

public class FactorPrime {
    public static List<Integer> primeFactors(int number) {
        int n = number;
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        return factors;
    }

    public static void main(String[] args) {
        System.out.println(primeFactors(21));
    }
}
