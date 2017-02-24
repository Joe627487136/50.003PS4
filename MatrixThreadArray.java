package Week5;


import java.util.Arrays;

/**
 * Created by zhouxuexuan on 24/2/17.
 */


public class MatrixThreadArray {
    public static int[][] A = {{1,3},{1,2},{5,3}};
    public static int[][] B = {{8,7,6}, {5,4,3}};
    public static int[][] C = new int[A.length][B[0].length];
    public static DimensinalWorkingThread[][] Threads = new DimensinalWorkingThread[A.length][B[0].length];


    public static void main(String[] args) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                Threads[i][j] = new DimensinalWorkingThread(i, j, A, B, C);
                Threads[i][j].start();
            }
        }
        System.out.println((Arrays.deepToString(C)));
    }
}


class DimensinalWorkingThread extends Thread{
    private int row;
    private int col;
    private int [][] A;
    private int [][] B;
    private int [][] C;

    public DimensinalWorkingThread(int row, int col, int[][] A,
                        int[][] B, int[][] C) {
        this.row = row;
        this.col = col;
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public void run() {
        C[row][col] = (A[row][0] * B[0][col])+ (A[row][1]*B[1][col]) ;
    }
}
