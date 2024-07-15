package org.example.models;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class HttpRequest {
    @NonNull
    private String method;
    @NonNull
    private String path;
    @NonNull
    private String body;
    private String token;
}
