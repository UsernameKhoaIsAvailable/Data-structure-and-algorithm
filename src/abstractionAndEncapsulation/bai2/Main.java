package abstractionAndEncapsulation.bai2;

public class Main {
    public static void test() {
        Vector vector = new Vector(1,2,3);
        Vector vector1 = new Vector(4,5,6);
        System.out.println(vector.plusVector(vector1));
        System.out.println(vector.subtractVector(vector1));
        System.out.println(vector.multiplyNumber(2));
        System.out.println(vector.multiplyVector(vector1));
    }

    public static void main(String[] args) {
        test();
    }
}
