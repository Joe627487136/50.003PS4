package Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerCE4 {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4321);
        System.out.println("(... expecting connection ...)");
        Socket clientSocket = serverSocket.accept();
        System.out.println("(... connection established ...)");
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String inputLine;
        String userInput;
        String userInput1;
        int count=0;
        while (count<=1000) {
            do {
                userInput1 = "Wife: Fuck u";
                out.println(userInput1);
                out.println(stdIn.readLine());
                out.flush();
                count++;
                if (count == 1000){
                    userInput1 = "Bye";
                }
            } while (!userInput1.equals("Bye"));
            serverSocket.close();
            clientSocket.close();
            out.close();
            in.close();
        }
    }
}