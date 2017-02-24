package Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TicTacToe {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4998);

        Socket[] sockets = new Socket[2];
        BufferedReader[] input = new BufferedReader[2];
        PrintWriter[] output = new PrintWriter[2];

        for (int i = 0; i < 2; i++) {
            sockets[i] = serverSocket.accept();
            input[i] = new BufferedReader(new InputStreamReader(sockets[i].getInputStream()));
            output[i] = new PrintWriter(sockets[i].getOutputStream(), true);
        }

        System.out.println("Both clients connected.");

        int[][] board = new int[3][3];
        int turn = 0;
        int move = 0;
        int count = 0;

        do {
            output[count].println(move);
            output[count].flush();
            move = Integer.valueOf(input[count].readLine());
            if (turn%2==0)
                board[move/10-1][move%10-1] = 9;
            if (turn%2!=0)
                board[move/10-1][move%10-1] = 1;
            printBoard(board);
            count = 1 - count;
            turn++;
        } while (!check9win(board));
        for (int i = 0; i < 2; i++) {
            sockets[i].close();
            input[i].close();
            output[i].close();
        }
        if(check9win(board)){
            System.out.println("P1 win!");
        }else {
            System.out.println("P2 win!");
        }

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
    private static boolean check9win (int[][] game) {
        if (game[0][0]+game[0][1]+game[0][2]==27||game[1][0]+game[1][1]+game[1][2]==27||game[2][0]+game[2][1]+game[2][2]==27) {
            return true;
        }
        if (game[0][0]+game[1][0]+game[2][0]==27||game[0][1]+game[1][1]+game[2][1]==27||game[0][2]+game[1][2]+game[2][2]==27) {
            return true;
        }
        if (game[0][0]+game[1][1]+game[2][2]==27||game[0][2]+game[1][1]+game[2][0]==27) {
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

