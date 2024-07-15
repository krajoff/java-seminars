package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class HttpRequest {
    private String method;
    private String path;
    private String body;
}
