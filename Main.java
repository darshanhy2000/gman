package gman;

public class Main {
    public static void main(String[] args) {
        Point start = new Point(2, 1);
        Point end = new Point(4, 5);
        Direction initialDirection = Direction.N;
        int initialPower = 200;

        GMan gman = new GMan(start, initialDirection, initialPower);
        int remainingPower = gman.calculateRemainingPower(end);

        if (remainingPower >= 0) {
            System.out.println("Remaining power: " + remainingPower);
        } else {
            System.out.println("No path found to the destination.");
        }
    }
}
