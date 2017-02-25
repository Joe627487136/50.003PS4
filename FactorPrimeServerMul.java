package Week5;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class FactorPrimeServerMul {

    public static void main (String[] args) throws Exception {
        BigInteger n = new BigInteger("21");
        ServerSocket serverSocket = new ServerSocket(4701);
        serverSocket.setSoTimeout(10000);//10s
        ArrayList<Socket> sockets = new ArrayList<Socket>();
        ArrayList<PrintWriter> outChannels = new ArrayList<PrintWriter>();
        while (true) {
            try {
                Socket newSocket = serverSocket.accept();
                sockets.add(newSocket);
                System.out.println(sockets.size() + " clients connected.");
                outChannels.add(new PrintWriter(newSocket.getOutputStream(), true));
            }
            catch (java.net.SocketTimeoutException e) {
                System.out.println("Timed out.");
                break;
            }
        }

        System.out.println(sockets.size()+"clients are in. Getting the reflection...");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < sockets.size(); i++) {
            PrintWriter out = new PrintWriter(sockets.get(i).getOutputStream(), true);
            out.println(n.toString());
            out.println(i+2);
            out.println(sockets.size());
            out.flush();
            out.close();
        }
        Socket client = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String result = in.readLine();
        serverSocket.close();

        long time = System.currentTimeMillis() - startTime;
        System.out.println("Factors are: " + result);
        System.out.print("Time passed: " + time);
    }
}
