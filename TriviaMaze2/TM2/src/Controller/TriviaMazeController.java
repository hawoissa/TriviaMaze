package Controller;

import GUIView.Frame;
import Model.Room;

import javax.swing.JFrame;
import java.awt.EventQueue;
import java.io.*;

public class TriviaMazeController {
    /**
     * Main method for Trivia Maze game. Will be entry point for game.
     */
    public static void main(String[] theArgs) {
        EventQueue.invokeLater(() -> {
            Frame frame = null;
            try {
                frame = new Frame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

    }
}
