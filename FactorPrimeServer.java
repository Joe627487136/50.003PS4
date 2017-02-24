package Week5;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class FactorPrimeServer {

    public static void main (String[] args) throws Exception {
        int n = 21;
        ServerSocket serverSocket = new ServerSocket(4700);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 3; i++) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("client entered.");
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(String.valueOf(n));
            out.println(i+2);
            out.flush();
            clientSocket.close();
            out.close();
        }

        System.out.println("Getting the reflection...");
        Socket client = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String result = in.readLine();
        serverSocket.close();

        long time = System.currentTimeMillis() - startTime;
        System.out.println("Factors are: " + result);
        System.out.print("Time passed: " + time);
    }
}
