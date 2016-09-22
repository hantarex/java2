package geekbrains.java_2.lesson_4;

import java.util.Scanner;

public class ClientThread implements Runnable {
    private Scanner in;
    private MainWindow mainWindow;

    public ClientThread(MainWindow mainWindow, Scanner in) {
        this.mainWindow = mainWindow;
        this.in=in;
    }

    @Override
    public void run() {
        String input;
        try {
            while (in.hasNextLine()) {
                input=in.nextLine();
                mainWindow.getText_area().text_area.append(input + "\n");
            }
        } finally {
            in.close();
        }
    }
}
