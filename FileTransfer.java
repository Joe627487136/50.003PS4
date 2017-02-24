package Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FileTransfer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4999);

        while (true) {
            Socket newSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));
            PrintWriter out = new PrintWriter(newSocket.getOutputStream(), true);
            PrintWriter printWriter = new PrintWriter("/Users/zhouxuexuan/desktop/output.txt");
            String inputLine;
            do {
                inputLine = in.readLine();
                out.println("pass my fiend");
                out.flush();
                if (!inputLine.equals("&&&NOMORE&&&")) {
                    printWriter.write(inputLine + "\r\n");
                }
            } while (!inputLine.equals("&&&NOMORE&&&"));
            out.println("pass my fiend");
            out.flush();
            printWriter.close();
            in.close();
            out.close();
            newSocket.close();
        }
    }
}

