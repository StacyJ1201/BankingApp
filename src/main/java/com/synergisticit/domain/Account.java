package com.synergisticit.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
public class Account {

    @Id
    private long accountId;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String accountHolder;

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate accountDateOpened;

    private double accountBalance;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer accountCustomer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch accountBranch;

}
