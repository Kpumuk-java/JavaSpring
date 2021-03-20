package ru.kpumuk.console.blog;

import java.util.List;
import java.util.Scanner;

public class BlogApp {



    private static void addChannel (String channelName) {

    }

    public static void main(String[] args) {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        try {
            while (!exit) {

                if (sc.equals("exit")) {
                    exit = true;
                }
            }
        } finally {
            sc.close();
        }
    }
}
