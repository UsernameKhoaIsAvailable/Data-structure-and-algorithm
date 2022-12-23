package abstractionAndEncapsulation.bai2;

public class Vector {
    private int[] vector = new int[3];

    public Vector(int a, int b, int c) {
        this.vector[0] = a;
        this.vector[1] = b;
        this.vector[2] = c;
    }

    public int[] getVector() {
        return vector;
    }

    public int[] plusVector(int d, int e, int f) {
        int[] result = new int[3];
        result[0] = vector[0] + d;
        result[1] = vector[1] + e;
        result[2] = vector[2] + f;
        return result;
    }

    public int[] subtractVector(int d, int e, int f) {
        int[] result = new int[3];
        result[0] = vector[0] - d;
        result[1] = vector[1] - e;
        result[2] = vector[2] - f;
        return result;
    }

    public int[] multiplyNumber(int d) {
        int[] result = new int[3];
        result[0] = vector[0] * d;
        result[1] = vector[1] * d;
        result[2] = vector[2] * d;
        return result;
    }

    public int multiplyVector(int d, int e, int f) {
        int result = 0;
        result += vector[0] * d;
        result += vector[1] * e;
        result += vector[2] * f;
        return result;
    }
}
