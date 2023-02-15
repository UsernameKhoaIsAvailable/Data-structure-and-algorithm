package Interface.bai1;

class MoveablePoint implements Moveable {
    int x;
    int y;
    int xSpeed;
    int ySpeed;

    public MoveablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    public String toString() {
        String result = "";
        result += String.format("(%d", x);
        result += String.format(",%d)", y);
        result += String.format(", speed(%d", xSpeed);
        result += String.format(",%d)", ySpeed);
        return result;
    }

    public void moveUp() {
        y += ySpeed;
    }

    public void moveDown() {
        y -= ySpeed;
    }

    public void moveLeft() {
        x -= xSpeed;
    }

    public void moveRight() {
        x += xSpeed;
    }
}
