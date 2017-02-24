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

public class ChatRoomClientNext {
    private Socket s;
    private BufferedReader br;
    private PrintWriter out;
    private boolean flag = true;

    public static void main(String[] args) {
        new ChatRoomClientNext().startUp();
    }

    private void startUp() {
        BufferedReader sbr = null;
        try {
            s = new Socket("127.0.0.1", 5858);
            out = new PrintWriter(s.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println("Client2");
            sbr = new BufferedReader(new InputStreamReader(System.in));
            new Thread(new ClientThread()).start();
            String str = null;
            while (flag && (str=sbr.readLine())!=null) {
                if (!flag) break;
                out.println(str);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
