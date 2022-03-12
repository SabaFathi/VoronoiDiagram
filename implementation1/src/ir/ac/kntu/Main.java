package ir.ac.kntu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        VoronoiDiagram voronoiDiagram = new VoronoiDiagram();
        voronoiDiagram.partitionCells();
        JFrame frame = new JFrame();
        frame.getContentPane()
                .add(new JLabel(new ImageIcon(voronoiDiagram.getCanvas())));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
