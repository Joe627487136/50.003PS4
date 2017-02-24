package Week5;

/**
 * Created by zhouxuexuan on 24/2/17.
 */

import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class ServerThread extends Thread{
    Socket socket=null;
    int clientnum;
    public ServerThread(Socket socket,int num) {
        this.socket=socket;
        clientnum=num+1;
    }
    public void run() {
        System.out.println("Go");
        try{
            long startTime = System.currentTimeMillis();
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());
            out.writeInt(21);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String result = in.readLine();
            socket.close();
            long duration = System.currentTimeMillis() - startTime;
            System.out.println("Factors are: " + result);
            System.out.println("Time elapsed: " + duration);
        }catch(Exception e){
            System.out.println("Error:"+e);
        }
    }

}
