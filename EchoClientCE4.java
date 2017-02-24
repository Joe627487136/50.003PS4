package Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class EchoClientCE4 {
    public static void main(String[] args) throws Exception {
        String hostName = "localhost";
        //String hostIP = "10.11.3.28";
        //String hostName = "fe80::7517:c1af:b2bb:da73%4";
        int portNumber = 4321;
 
//        Socket echoSocket = new Socket(hostName, portNumber);
		Socket echoSocket = new Socket();
		SocketAddress sockaddr = new InetSocketAddress("localhost", portNumber);
		echoSocket.connect(sockaddr, 100);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        String userInput;
        String userInput1;
        int count=0;
        while (count<=1000) {
            do {
                userInput1 = "Husband: Fuck u";
                out.println(userInput1);
                out.flush();
                count++;
                if (count == 1000){
                    userInput1 = "Bye";
                }
            } while (!userInput1.equals("Bye"));

            echoSocket.close();
            out.close();
        }
    }
}
