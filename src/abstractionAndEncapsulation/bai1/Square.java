package abstractionAndEncapsulation.bai1;

public class Square {
    private int length;
    private String color;

    public Square(int length) {
        this.length = length;
    }

    public Square(String color) {
        this.color = color;
    }

    public Square(int length, String color) {
        this.length = length;
        this.color = color;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int calArea(){
        return length*length;
    }
}

