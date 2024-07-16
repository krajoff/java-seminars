package org.example.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class Transaction {
    private Long id;
    private String fromUser;
    private String toUser;
    private Double amount;
    private LocalDateTime timestamp;
}
