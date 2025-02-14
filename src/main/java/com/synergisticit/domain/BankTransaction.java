package com.synergisticit.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bankTransactionId;

    @NonNull
    private double amount;

    private long bankTransactionFromAccount; // Will be used for withdrawal and transfer

    private long bankTransactionToAccount; //Used for deposit and transfer

    private BankTransactionType bankTransactionType;

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime bankTransactionDateTime;

    private String username;

    @NotEmpty
    private String comments;
}
