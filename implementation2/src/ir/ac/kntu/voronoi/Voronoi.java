package ir.ac.kntu.voronoi;

import javafx.geometry.Point2D;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Voronoi {

    public static void main(String[] args) {
        ArrayList<Point2D> points = new ArrayList<>();

        System.out.println("Please enter number of points. And then enter x & y of each point.");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(int i=0; i<n; i++){
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            points.add(new Point2D(x, y));
        }
        scanner.close();

        Voronoi v = new Voronoi(points,500, 500);
        v.partition();

        JFrame frame = new JFrame("voronoi");
        frame.getContentPane().add(new JLabel(new ImageIcon(v.voronoiDiagram)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private class Point{
        private double x;
        private double y;
        private int color;

        public Point(double x, double y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    private BufferedImage voronoiDiagram;
    private List<Point> points;
    private final int width;
    private final int hight;

    public Voronoi(List<Point2D> points, int width, int hight) {
        this.points = new ArrayList<>();
        for(Point2D point : points){
            this.points.add(new Point(point.getX(), point.getY(), (int)(1_000_000 * Math.random())));
        }
        this.width = width;
        this.hight = hight;
        voronoiDiagram = new BufferedImage(width, hight, BufferedImage.TYPE_INT_RGB);
    }

    public void partition(){
        for( int x=0 ; x < width ; x++){
            for( int y=0 ; y < hight ; y++){
                double closestDistance = width * hight;
                for(int k=0 ; k < points.size() ; k++){
                    double distance = distance(points.get(k).x, points.get(k).y, x, y);
                    if(distance < closestDistance){
                        closestDistance = distance;
                        voronoiDiagram.setRGB(x, y, points.get(k).color);
                    }

                    int pointRadius = 3;
                    if((points.get(k).x-pointRadius < x && x < points.get(k).x+pointRadius) &&
                            (points.get(k).y-pointRadius < y && y < points.get(k).y+pointRadius)){
                        voronoiDiagram.setRGB(x, y, 0x000000);
                    }
                }
            }
        }
    }

    private static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }
}
