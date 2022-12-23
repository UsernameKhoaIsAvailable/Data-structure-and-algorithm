package abstractionAndEncapsulation.bai2;

import java.util.Arrays;

public class Main {
    public static void test() {
        Vector vector = new Vector(1,2,3);
        int[] result1 = vector.getVector();
        int[] result2 = vector.plusVector(4,5,6);
        int[] result3 = vector.subtractVector(4,5,6);
        int[] result4 = vector.multiplyNumber(4);
        int result5 = vector.multiplyVector(4,5,6);

        for(int i = 0; i < 3; i++) {
            System.out.print(result1[i]);
            if (i != 2) {
                System.out.print(',');
            }
        }

        System.out.println();

        for(int i = 0; i < 3; i++) {
            System.out.print(result2[i]);
            if (i != 2) {
                System.out.print(',');
            }
        }

        System.out.println();

        for(int i = 0; i < 3; i++) {
            System.out.print(result3[i]);
            if (i != 2) {
                System.out.print(',');
            }
        }

        System.out.println();

        for(int i = 0; i < 3; i++) {
            System.out.print(result4[i]);
            if (i != 2) {
                System.out.print(',');
            }
        }

        System.out.println();
        System.out.print(result5);
    }

    public static void main(String[] args) {
        test();
    }
}
