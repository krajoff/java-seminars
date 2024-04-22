package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    static String document = "{\"description\": { \"participantInn\": \"string\" }, " +
            "\"doc_id\": \"string\", \"doc_status\": \"string\", " +
            "\"doc_type\": \"LP_INTRODUCE_GOODS\", 109 " +
            "\"importRequest\": true, \"owner_inn\": \"string\", " +
            "\"participant_inn\": \"string\", \"producer_inn\": \"string\", " +
            "\"production_date\": \"2020-01-23\", \"production_type\": \"string\", " +
            "\"products\": [ { \"certificate_document\": \"string\", " +
            "\"certificate_document_date\": \"2020-01-23\", " +
            "\"certificate_document_number\": \"string\", " +
            "\"owner_inn\": \"string\", \"producer_inn\": \"string\", " +
            "\"production_date\": \"2020-01-23\", \"tnved_code\": \"string\", " +
            "\"uit_code\": \"string\", \"uitu_code\": \"string\" } ], " +
            "\"reg_date\": \"2020-01-23\", \"reg_number\": \"string\"}";
    static String signature = "\"signature\": \"secret\"";

    //static String postEndPoint = "https://ismp.crpt.ru/api/v3/lk/documents/create";
    static String postEndPoint = "http://localhost:8000";

    public static void main(String[] args) throws IOException, InterruptedException {
        String requestJson = "{\"document\":" + document + ", " + signature + "}";
        httpPostRequest(requestJson);
    }

    public static void httpPostRequest(String requestJson)
            throws IOException, InterruptedException {
        var client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(postEndPoint))
                .version(HttpClient.Version.HTTP_1_1)
                .POST(HttpRequest.BodyPublishers.ofString(requestJson))
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        System.out.println("Response: " + responseBody);
    }
}
