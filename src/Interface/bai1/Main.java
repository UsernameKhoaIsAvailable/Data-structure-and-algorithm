package Interface.bai1;

public class Main {
    public static void test() {
        MoveablePoint point1 = new MoveablePoint(1, 2, 5,5);
        MoveableCircle circle1 = new MoveableCircle(point1, 2);
        point1.moveUp();
        point1.moveRight();
        System.out.println(point1);
        circle1.moveDown();
        circle1.moveLeft();
        System.out.println(circle1);
    }

    public static void main(String[] args) {
        test();
    }
}
