package Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        int[] SocketArray = {4321,4320,4319};
        for (int i = 0; i<SocketArray.length; i++){
            ServerSocket serverSocket = new ServerSocket(SocketArray[i]);
            System.out.println("(... expecting connection ...)");
            Socket clientSocket = serverSocket.accept();
            System.out.println("(... connection established ...)");
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader stdIn =
                    new BufferedReader(
                            new InputStreamReader(System.in));
            String inputLine;
            while (!((inputLine = in.readLine()).equals("Bye"))) {
                System.out.println("Wife says:" + inputLine);
                out.println(stdIn.readLine());
                out.flush();
            }
            out.println(inputLine);
            serverSocket.close();
            clientSocket.close();
            out.close();
            in.close();
        }
    }
}