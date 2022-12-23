package abstractionAndEncapsulation.bai1;

public class Round {
    private int radius;

    public Round(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int calArea(){
        return (int)Math.PI*radius*radius;
    }
}
