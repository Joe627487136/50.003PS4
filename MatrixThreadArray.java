package Week5;


import java.util.Arrays;

/**
 * Created by zhouxuexuan on 24/2/17.
 */


public class MatrixThreadArray {
    public static int[][] A = {{1, 3}, {1, 2}, {5, 3}};
    public static int[][] B = {{8, 7, 6}, {5, 4, 3}};
    public static int[][] C = new int[A.length][B[0].length];
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        int numberOfThreads = 2;
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new MatrixMultiThread(A,B,C,i*A.length/numberOfThreads,(i+1)*A.length/numberOfThreads);
            threads[i].start();
        }

        try {
            for (int i = 0; i < numberOfThreads; i++) {
                threads[i].join();
            }
            long duration = System.currentTimeMillis() - startTime;
            System.out.print("Time flied: " + duration+"\n");
            System.out.println(Arrays.deepToString(C));
        }
        catch (InterruptedException e) {
            System.out.println("A thread didn't finish!");
        }
    }

    static class MatrixMultiThread extends Thread {
        private int[][] A;
        private int[][] B;
        private int[][] C;
        int lower;
        int upper;

        public MatrixMultiThread(int[][] A, int[][] B, int[][] C, int lower, int upper) {
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
                        C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
    }
}
