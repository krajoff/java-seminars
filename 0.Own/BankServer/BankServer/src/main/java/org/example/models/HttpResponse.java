package org.example.models;

import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    public static void send(PrintWriter out, int statusCode, String responseBody) {
        String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());

        String contentType = "application/json";

        Map<String, String> headers = new HashMap<>();
        headers.put("Date", date);
        headers.put("Content-Type", contentType);
        headers.put("Content-Length", String.valueOf(responseBody.length()));
        headers.put("Connection", "close");

        out.println("HTTP/1.1 " + statusCode + " "
                + HttpStatus.getStatusMessage(statusCode));

        headers.entrySet()
                .stream()
                .map(header -> header.getKey() + " " + header.getValue())
                .forEach(out::println);

        out.println();
        out.println(responseBody);
        out.flush();
    }

}
