package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpStatusImageDownloader {
    private final OkHttpClient httpClient = new OkHttpClient();
    private final String baseUrl = "https://http.cat/";

    public void downloadStatusImage(int code) throws Exception {
        HttpStatusChecker checker = new HttpStatusChecker();
        String imageUrl = checker.getStatusImage(code);

        Request request = new Request.Builder().url(imageUrl).build();

        try {
            Response response = httpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    InputStream inputStream = body.byteStream();
                    String fileName = code + ".jpg";

                    try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                    System.out.println("Image for status code " + code + " downloaded as " + fileName);
                } else {
                    throw new Exception("Failed to get response body");
                }
            } else {
                throw new Exception("HTTP request failed with status code: " + response.code());
            }
        } catch (IOException e) {
            throw new Exception("Failed to download image: " + e.getMessage());
        }
    }
}