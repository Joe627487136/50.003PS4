package Week5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;

public class FileTransferClient {
    public static void main(String[] args) throws Exception {
        String hostName = "127.0.0.1";
        int portNumber = 4999;

        Socket echoSocket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        BufferedReader is = new BufferedReader(new FileReader("/Users/zhouxuexuan/desktop/input.txt"));
        String inputLine = is.readLine();
        System.out.println(inputLine);
        echoSocket.setSoTimeout(5000);
        while (inputLine != null) {
            while (true) {
                out.println(inputLine);
                out.flush();
                try {
                    in.readLine();
                    break;
                }
                catch (java.net.SocketTimeoutException e) {
                }
            }
            inputLine = is.readLine();
            System.out.println(inputLine);
        }
        while (true) {
            out.println("&&&NOMORE&&&");
            try {
                inputLine = in.readLine();
                break;
            }
            catch (java.net.SocketTimeoutException e) {
            }
        }

        is.close();
        echoSocket.close();
        in.close();
        out.close();
    }
}

