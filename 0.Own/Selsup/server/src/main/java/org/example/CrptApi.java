package org.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


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
    Document() {}
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

public class CrptApi {
    TimeUnit timeUnit;
    int requestLimit;

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.timeUnit = timeUnit;
        this.requestLimit = requestLimit;
    }

    public Document createDocumentViaSignature(Object document, String signature) {
        String correct_signature = "1";
        if (document instanceof Document && signature.equals(correct_signature)) {
            return (Document) document;
        }
        return null;
    }
}
