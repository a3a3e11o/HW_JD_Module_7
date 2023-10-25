package org.example;

import java.util.Scanner;

public class HttpImageStatusCli {
    public void askStatus() {
        Scanner scanner = new Scanner(System.in);
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();

        while (true) {
            System.out.print("Enter HTTP status code: ");
            if (scanner.hasNextInt()) {
                int statusCode = scanner.nextInt();
                scanner.nextLine();

                try {
                    downloader.downloadStatusImage(statusCode);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } else {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                System.err.println("Please enter a valid number or type 'exit' to quit.");
            }
        }

        scanner.close();
    }
}