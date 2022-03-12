package ir.ac.kntu;

import java.util.Random;

public class RandomPoint {
    private int x;
    private int y;
    private int color;
    private Random randomGenerator;

    public RandomPoint(int xBound, int yBound, int color) {
        randomGenerator = new Random();
        x = randomGenerator.nextInt(500);
        y = randomGenerator.nextInt(500);
        this.color = color;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getColor() {
        return color;
    }
    public double getDistance(int x, int y){
        double xDifference = this.x - x;
        double yDifference = this.y - y;

        return Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));
    }
}
