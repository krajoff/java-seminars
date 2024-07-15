package org.example.models;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Transaction {
    private Long id;
    private String fromUser;
    private String toUser;
    private Double amount;
    private LocalDateTime timestamp;
}
