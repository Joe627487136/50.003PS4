package Week5;

import java.util.Arrays;

public class MatrixThread {
    public static int[][] A = {{1,3},{1,2},{5,3}};
    public static int[][] B = {{8,7,6}, {5,4,3}};
    public static int[][] C = new int[A.length][B[0].length];

    public static MatrixMultiThread thread2 = new MatrixMultiThread(A,B,C,0,A.length/2+1);
    public static MatrixMultiThread thread1 = new MatrixMultiThread(A,B,C,A.length/2+1,A.length);
    public static void main(String[] args) throws Exception {
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(Arrays.deepToString(C));
    }
}

class MatrixMultiThread extends Thread {
    private int [][] A;
    private int [][] B;
    private int [][] C;
    int lower;
    int upper;

    public MatrixMultiThread (int[][] A, int[][] B, int[][] C, int lower, int upper) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.lower = lower;
        this.upper = upper;
    }

    public void run() {
        for (int i = lower; i < upper; i++) {
            for (int j = 0; j < C[i].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    C[i][j] += A[i][k]*B[k][j];
                }
            }
        }
    }
}
