package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpStatusChecker {
    private final OkHttpClient httpClient = new OkHttpClient();

    public String getStatusImage(int code) throws Exception {
        String baseUrl = "https://http.cat/";
        String url = baseUrl + code + ".jpg";
        Request request = new Request.Builder().url(url).build();

        try {
            Response response = httpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                return url;
            } else if (response.code() == 404) {
                throw new Exception("There is not image for HTTP status " + code);
            } else {
                throw new Exception("HTTP request failed with status code: " + response.code());
            }
        } catch (Exception e) {
            throw new Exception("Failed to fetch image: " + e.getMessage());
        }
    }
}
