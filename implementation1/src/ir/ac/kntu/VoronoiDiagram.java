package ir.ac.kntu;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoronoiDiagram {
    private final static int WIDTH = 500;
    private final static int HEIGHT = 500;
    final int BACKGROUND_COLOR = 0xFFFFFF;
    final int POINT_COLOR = 0x000000;
    final int[] CELL_COLORS = {
            0xFF0000, 0x00FF00, 0x0000FF,
            0x00FFFF, 0xFF00FF, 0xFFFF00,
            0x666666, 0x119955, 0xAA4488
    };

    private BufferedImage canvas;
    private List<RandomPoint> randomPoints;

    public VoronoiDiagram(){
        canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        setCanvasBackgroundColor();
        generateRandomPoints();
    }
    private void setCanvasBackgroundColor(){
        for(int x=0; x<WIDTH; x++){
            for(int y=0; y<HEIGHT; y++){
                canvas.setRGB(x, y, BACKGROUND_COLOR);
            }
        }
    }
    private void generateRandomPoints(){
        randomPoints = new ArrayList<RandomPoint>();
        Arrays.stream(CELL_COLORS).forEach(color ->
                randomPoints.add(new RandomPoint(WIDTH, HEIGHT, color)));
    }

    public void partitionCells(){
        colorCells();
        colorPoints();
    }
    private void colorCells(){
        for(int x=0; x<WIDTH; x++){
            for(int y=0 ; y<HEIGHT; y++){
                RandomPoint closestPoint = findClosestPoint(x, y);
                if(closestPoint != null){
                    canvas.setRGB(x, y, closestPoint.getColor());
                }
            }
        }
    }
    private void colorPoints(){
        final int pointRadius = 3;
        for(int x=0; x<WIDTH; x++){
            for(int y=0; y<HEIGHT; y++){
                for (RandomPoint point : randomPoints){
                    if((point.getX()-pointRadius < x && x < point.getX()+pointRadius) &&
                            (point.getY()-pointRadius < y && y < point.getY()+pointRadius)){
                        canvas.setRGB(x, y, POINT_COLOR);
                    }
                }
            }
        }
    }
    private RandomPoint findClosestPoint(int x, int y){
        double closestDistance = WIDTH * HEIGHT;
        RandomPoint closestPoint = null;
        for (RandomPoint point : randomPoints){
            double distance = point.getDistance(x, y);
            if(distance < closestDistance){
                closestDistance = distance;
                closestPoint = point;
            }
        }
        return closestPoint;
    }

    public BufferedImage getCanvas(){
        return canvas;
    }
}
