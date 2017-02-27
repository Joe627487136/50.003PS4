package Week5;

import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class FactorPrimeClient {
    public static void main(String[] args) throws Exception {
        String hostName = "127.0.0.1";
        int portNumber = 4700;
        int portNumber2 = 4701;
        try{
            Socket echoSocket = new Socket(hostName, portNumber);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            smallestPrimeFactor factorPrime = new smallestPrimeFactor();
            BigInteger n = new BigInteger(in.readLine());
            in.close();
            echoSocket.close();

            System.out.println("start factoring.");
            BigInteger ans1 = factorPrime.smallestPrimeFactor(n);
            BigInteger ans2 = n.divide(ans1);
            String ans = "Small prime: "+ans1+" | "+"Big prime: "+ans2;
            System.out.println("done factoring.");

            try {
                Socket Socket = new Socket(hostName, portNumber2);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                out.println(ans);
                out.flush();
                echoSocket.close();
                out.close();
            }
            catch (Exception e) {
            }
        }catch (Exception e){
            System.out.println("Task finished");
        }
    }
}
