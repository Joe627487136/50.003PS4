package Week5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouxuexuan on 24/2/17.
 */

public class smallestPrimeFactor {
    public static BigInteger smallestPrimeFactor(BigInteger n) {
        BigInteger two = new BigInteger("2");
        if (n.mod(two).compareTo(BigInteger.ZERO) == 0) {
            return two;
        }
        for (BigInteger i = new BigInteger("3"); n.compareTo(i) >= 0; i = i.add(two)) {
            if (n.mod(i).compareTo(BigInteger.ZERO) == 0)
                return i;
        }
        return BigInteger.ZERO;
    }

    public static void main(String[] args) {
        System.out.println(smallestPrimeFactor(BigInteger.valueOf(21)));
    }
}
