package abstractionAndEncapsulation.bai2;

import java.util.Arrays;

public class Vector {
    private int[] components = new int[3];

    public Vector(int a, int b, int c) {
        this.components[0] = a;
        this.components[1] = b;
        this.components[2] = c;
    }

    public int[] getComponents() {
        return components;
    }

    public Vector plusVector(Vector vector) {
        Vector result = new Vector(0,0,0);
        for(int i = 0; i < 3; i++) {
            result.components[i] = components[i] + vector.components[i];
        }
        return result;
    }

    public Vector subtractVector(Vector vector) {
        Vector result = new Vector(0,0,0);
        for(int i = 0; i < 3; i++) {
            result.components[i] = components[i] - vector.components[i];
        }
        return result;
    }

    public Vector multiplyNumber(int d) {
        Vector result = new Vector(0,0,0);
        result.components[0] = components[0] * d;
        result.components[1] = components[1] * d;
        result.components[2] = components[2] * d;
        return result;
    }

    public int multiplyVector(Vector vector) {
        int result = 0;
        for(int i = 0; i < 3; i++) {
            result += components[i] * vector.components[i];
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";

        for(int i = 0; i < 3; i++) {
            result += Integer.toString(components[i]);
            if (i != 2) {
                result += ",";
            }
        }

        return result;
    }
}
