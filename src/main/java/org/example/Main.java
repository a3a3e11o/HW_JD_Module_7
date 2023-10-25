package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("===============HttpStatusChecker===============");
        HttpStatusChecker checker = new HttpStatusChecker();
        try {
            String image = checker.getStatusImage(200);
            System.out.println("Image URL for status code 200: " + image);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("===============HttpStatusImageDownloader========");
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
        try {
            downloader.downloadStatusImage(200);
            downloader.downloadStatusImage(404);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("===============HttpImageStatusCli===============");
        HttpImageStatusCli cli = new HttpImageStatusCli();
        cli.askStatus();
    }
}