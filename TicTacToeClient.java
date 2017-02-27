package Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeClient {
    public static void main(String[] args) throws Exception {
        String hostName = "127.0.0.1";
        int portNumber = 4998;

        Socket echoSocket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        int[][] board = new int[3][3];
        int turn = 0;
        while (true) {
            int move = Integer.valueOf(in.readLine());
            if (move != 0) {
                board[move/10-1][move%10-1] = 9;
                printBoard(board);
                if (checkwin(board)) {
                    break;
                }
            }

            String input = stdIn.readLine();
            int myMove = Integer.valueOf(input);
            out.println(myMove);
            out.flush();
            board[myMove/10-1][myMove%10-1] = 1;
            printBoard(board);
            if (checkwin(board)) {
                break;
            }
        }

        in.close();
        out.close();
        echoSocket.close();
    }

    private static boolean checkwin (int[][] game) {
        if (game[0][0]+game[0][1]+game[0][2]==27||game[1][0]+game[1][1]+game[1][2]==27||game[2][0]+game[2][1]+game[2][2]==27) {
            return true;
        }
        if ( game[0][0]+game[0][1]+game[0][2]==3||game[1][0]+game[1][1]+game[1][2]==3||game[2][0]+game[2][1]+game[2][2]==3) {
            return true;
        }
        if (game[0][0]+game[1][0]+game[2][0]==27||game[0][1]+game[1][1]+game[2][1]==27||game[0][2]+game[1][2]+game[2][2]==27) {
            return true;
        }
        if (game[0][0]+game[1][0]+game[2][0]==3||game[0][1]+game[1][1]+game[2][1]==3||game[0][2]+game[1][2]+game[2][2]==3) {
            return true;
        }
        if (game[0][0]+game[1][1]+game[2][2]==27||game[0][2]+game[1][1]+game[2][0]==27) {
            return true;
        }
        if (game[0][0]+game[1][1]+game[2][2]==3||game[0][2]+game[1][1]+game[2][0]==3) {
            return true;
        }

        return false;
    }

    public static void printBoard(int[][] board) {
        System.out.println("-------------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
}

