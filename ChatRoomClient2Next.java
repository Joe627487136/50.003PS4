package Week5;

/**
 * Created by zhouxuexuan on 24/2/17.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatRoomClient2Next {
    private Socket s;
    private BufferedReader br;
    private PrintWriter out;
    private boolean flag = true;
    private String clientname = "Client2";

    public static void main(String[] args) {
        new ChatRoomClient2Next().startUp();
    }

    private void startUp() {
        BufferedReader sbr = null;
        try {
            s = new Socket("127.0.0.1", 5858);
            out = new PrintWriter(s.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println(clientname);
            sbr = new BufferedReader(new InputStreamReader(System.in));
            new Thread(new ClientThread()).start();
            long startTime = System.currentTimeMillis();
            String str = null;
            while (flag && (str=sbr.readLine())!=null) {
                long duration = System.currentTimeMillis()-startTime;
                if (duration>5000){
                    flag = false;
                }
                if (!flag) break;
                out.println(str);
            }
            System.out.println("5s passed, "+clientname+" u are not able to talk more");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }catch (java.net.SocketException e){
            System.out.println("No socket to connect");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (sbr != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void receive() {
        try {
            String rs = br.readLine();
            if (rs.equalsIgnoreCase("disconnect")) {
                flag = false;
                System.out.println("press enter to leave");
            }
            System.out.println(rs);
        } catch (IOException e) {
            System.out.println("No socket to connect");
        }
    }

    private class ClientThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (!flag) break;
                receive();
            }
        }

    }

}
