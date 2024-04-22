package org.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@JsonAutoDetect
class Request {
    @JsonProperty("document")
    Document document;
    @JsonProperty("signature")
    String signature;

    @Override
    public String toString() {
        return "Request{" +
                "document=" + document +
                ", signature='" + signature + '\'' +
                '}';
    }
}

@JsonAutoDetect
class Document {
    @JsonProperty("description")
    Map<String, String> description;
    @JsonProperty("doc_id")
    String doc_id;
    @JsonProperty("doc_status")
    String doc_status;
    @JsonProperty("doc_type")
    String doc_type;
    @JsonProperty("importRequest")
    Boolean importRequest;
    @JsonProperty("owner_inn")
    String owner_inn;
    @JsonProperty("participant_inn")
    String participant_inn;
    @JsonProperty("producer_inn")
    String producer_inn;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate production_date;
    @JsonProperty("production_type")
    String production_type;
    @JsonProperty("products")
    List<Product> products;
    @JsonProperty("reg_date")
    LocalDate reg_date;
    @JsonProperty("reg_number")
    String reg_number;

    Document() {
    }

    @Override
    public String toString() {
        return "Document{" +
                "description=" + description +
                ", doc_id='" + doc_id + '\'' +
                ", doc_status='" + doc_status + '\'' +
                ", doc_type='" + doc_type + '\'' +
                ", importRequest=" + importRequest +
                ", owner_inn='" + owner_inn + '\'' +
                ", participant_inn='" + participant_inn + '\'' +
                ", producer_inn='" + producer_inn + '\'' +
                ", production_date=" + production_date +
                ", production_type='" + production_type + '\'' +
                ", products=" + products +
                ", reg_date=" + reg_date +
                ", reg_number='" + reg_number + '\'' +
                '}';
    }
}

class Product {
    @JsonProperty("certificate_document")
    String certificate_document;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate certificate_document_date;
    @JsonProperty("certificate_document_number")
    String certificate_document_number;
    @JsonProperty("owner_inn")
    String owner_inn;
    @JsonProperty("producer_inn")
    String producer_inn;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate production_date;
    @JsonProperty("tnved_code")
    String tnved_code;
    @JsonProperty("uit_code")
    String uit_code;
    @JsonProperty("uitu_code")
    String uitu_code;

    @Override
    public String toString() {
        return "Product{" +
                "certificate_document='" + certificate_document + '\'' +
                ", certificate_document_date=" + certificate_document_date +
                ", certificate_document_number='" + certificate_document_number + '\'' +
                ", owner_inn='" + owner_inn + '\'' +
                ", producer_inn='" + producer_inn + '\'' +
                ", production_date=" + production_date +
                ", tnved_code='" + tnved_code + '\'' +
                ", uit_code='" + uit_code + '\'' +
                ", uitu_code='" + uitu_code + '\'' +
                '}';
    }
}

class CustomRateLimiter {
    RateLimiter rateLimiter;
    public CustomRateLimiter(TimeUnit timeUnit, int requestLimit, String name) {
        RateLimiterConfig config = RateLimiterConfig.custom().
                limitForPeriod(requestLimit).
                limitRefreshPeriod(Duration.ofSeconds(TimeUnit.SECONDS.convert(1, timeUnit))).
                timeoutDuration(Duration.ofMillis(50)).build();
        RateLimiterRegistry registry = RateLimiterRegistry.of(config);
        rateLimiter = registry.rateLimiter(name);
        rateLimiter.getEventPublisher().onSuccess(System.out::println);
        rateLimiter.getEventPublisher().onFailure(System.out::println);
    }
}

class CustomHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {

        var isr = new InputStreamReader(t.getRequestBody(), StandardCharsets.UTF_8);
        var br = new BufferedReader(isr);
        var json = br.readLine();
        var json_ = json.replace("109 ", ""); // удаление 109

        // Десериализация из json
        var reader = new StringReader(json_);
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Request request = objectMapper.readValue(reader, Request.class);
        Document document = request.document;
        String signature = request.signature;
        System.out.println("Request_document: " + document);
        System.out.println("Request_signature: " + signature);

        Supplier<Document> restrictedSupplier = RateLimiter
                .decorateSupplier(Main.crptApi.rateLimiter,
                        () -> Main.crptApi.createDocumentViaSignature
                                (document, signature));
        restrictedSupplier.get();

        // Сериализация в json
        var writer = new StringWriter();
        objectMapper.writeValue(writer, document);
        String response = writer.toString();
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

public class CrptApi {
    TimeUnit timeUnit;
    int requestLimit;
    RateLimiter rateLimiter;

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.timeUnit = timeUnit;
        this.requestLimit = requestLimit;
        CustomRateLimiter customRateLimiter = new CustomRateLimiter(timeUnit,
                requestLimit, "createDocumentViaSignature");
        rateLimiter = customRateLimiter.rateLimiter;
    }

    public Document createDocumentViaSignature(Object document, String signature) {
        String correct_signature = "secret";
        if (document instanceof Document && signature.equals(correct_signature)) {
            System.out.println("Good");
            return (Document) document;
        }
        System.out.println("Bad");
        return null;
    }
}
