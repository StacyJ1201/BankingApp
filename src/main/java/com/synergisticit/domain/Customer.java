package com.synergisticit.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
public class Customer {

    @Id
    private long customerId;

    private String customerName;

    private Gender customerGender;

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate customerDOB;

    @Embedded
    private Address customerAddress;

    private String customerSSN;

    @JsonManagedReference
    @OneToMany(mappedBy="accountCustomer")
    private List<Account> customerAccounts = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId", unique=true)
    @NotNull(message = "Customer must be associated with a user")
    private User user;
}
