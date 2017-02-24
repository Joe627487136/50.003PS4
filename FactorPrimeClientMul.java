package Week5;

import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class FactorPrimeClientMul {
    public static void main(String[] args) throws Exception {
        String hostName = "127.0.0.1";
        int portNumber = 4701;
        try{
            Socket echoSocket = new Socket(hostName, portNumber);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            FactorPrime factorPrime = new FactorPrime();
            int n = Integer.valueOf(in.readLine());
            in.close();
            echoSocket.close();

            System.out.println("start factoring.");
            String ans = factorPrime.primeFactors(n).toString();
            System.out.println("done factoring.");

            try {
                echoSocket = new Socket(hostName, portNumber);
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
