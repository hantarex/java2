package geekbrains.java_2.testSystemIn;

import sun.awt.windows.ThemeReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static java.lang.System.console;

public class MainClass {
    public static void main(String[] args) {
        String data = "Hello, World!\r\n";
        InputStream stdin = System.in;
        try {
            Scanner scanner = new Scanner(System.in);
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Thread th= new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(scanner.nextLine());
                }
            });
            th.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            th.interrupt();
            System.out.println("ok");
            System.setIn(new ByteArrayInputStream("My string".getBytes()));
            System.setIn(System.in);

            scanner.close();
        } finally {
            System.setIn(stdin);
        }
    }
}
