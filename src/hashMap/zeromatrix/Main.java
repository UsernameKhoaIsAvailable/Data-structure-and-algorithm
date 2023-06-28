package hashMap.zeromatrix;

public class Main {
    public static void zeroMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroMatrixProcess(matrix);
                    return;
                }
            }
        }
    }

    public static void zeroMatrixProcess(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public static void test1() {
        int[][] matrix1 = new int[2][3];

        matrix1[0][0] = 1;
        matrix1[0][1] = 2;
        matrix1[0][2] = 3;
        matrix1[1][0] = 4;
        matrix1[1][1] = 5;
        matrix1[1][2] = 6;

        zeroMatrix(matrix1);

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                System.out.print(matrix1[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void test2() {
        int[][] matrix2 = new int[3][3];

        matrix2[0][0] = -1;
        matrix2[0][1] = 0;
        matrix2[0][2] = 2;
        matrix2[1][0] = 0;
        matrix2[1][1] = 4;
        matrix2[1][2] = 7;
        matrix2[2][0] = -3;
        matrix2[2][1] = -4;
        matrix2[2][2] = -2;

        zeroMatrix(matrix2);

        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                System.out.print(matrix2[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        test1();
        System.out.println();
        test2();
    }
}



