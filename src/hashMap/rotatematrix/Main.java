package hashMap.rotatematrix;

import java.util.Arrays;

public class Main {
    public static int[][] rotateMatrix(int[][] image) {
       int size = image.length * image[0].length;
       int[] storedE = new int[size];
       int k = 0;

       for (int i = 0; i < image.length; i++) {
           for (int j = 0; j < image[0].length; j++) {
               storedE[k] = image[i][j];
               k++;
           }
       }

       k -= 1;

        for (int i = 0; i < image.length; i++) {
            for (int j = image[0].length - 1; j > -1 ; j--) {
                image[j][i] = storedE[k];
                k--;
            }
        }

        return image;
    }

    public static void test() {
        int[][] example = new int[3][3];
        example[0][0] = 2;
        example[0][1] = 9;
        example[0][2] = 0;
        example[1][0] = 1;
        example[1][1] = 3;
        example[1][2] = 5;
        example[2][0] = 8;
        example[2][1] = 1;
        example[2][2] = 5;

        int[][] rotatedExample = new int[3][3];
        rotatedExample[0][0] = 2;
        rotatedExample[0][1] = 1;
        rotatedExample[0][2] = 2;
        rotatedExample[1][0] = 4;
        rotatedExample[1][1] = 3;
        rotatedExample[1][2] = 9;
        rotatedExample[2][0] = 7;
        rotatedExample[2][1] = 5;
        rotatedExample[2][2] = 0;
        example = rotateMatrix(example);

        for (int i = 0; i < example.length; i++) {
            for (int j = 0; j < example[0].length; j++) {
                System.out.print(example[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
            test();
    }
}
