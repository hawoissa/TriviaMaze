package GUIView;

import java.io.File;
import java.util.Scanner;

public class TestSystemSound {
    public static void main(String[] args) {
        try {
            while (true) {
                Scanner input = new Scanner(System.in);
                System.out.println("1. Start, 2. Won, 3. Over, 4. Duration, 5. Won and End");
                int num = input.nextInt();
                if (num == 1) {
                    SystemSound sound = new SystemSound(new File("mixkit-small-crowd-laugh-and-applause-422.wav"));
                    //sound.gameStartSound();
                    sound.gameSounds();
                } else if (num == 2) {
                    SystemSound sound = new SystemSound(new File("get-question-right.wav"));
                    //sound.gameWonSound();
                    sound.gameSounds();
                } else if (num == 3) {
                    SystemSound sound = new SystemSound(new File("game-over-471.wav"));
                    //sound.gameOverSound();
                    sound.gameSounds();
                }
                else if (num == 4) {
                    SystemSound sound = new SystemSound(new File("clock-timer-1045.wav"));
                    //sound.gameOverSound();
                    sound.gameSounds();
                }
                else if (num == 5) {
                    SystemSound sound = new SystemSound(new File("Game-Won.wav"));
                    //sound.gameOverSound();
                    sound.gameSounds();
                }
                else {
                    System.exit(0);
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
