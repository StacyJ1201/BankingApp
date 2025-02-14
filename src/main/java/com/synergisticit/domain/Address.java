package com.synergisticit.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Address {

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    private String phoneNumber;


    public String getFormattedAddress(){
        return String.format("Address Line 1: %s, Address Line 2: %s, City: %s, State: %s, Country: %s, ZipCode: %s, (Tel: %s)",
                this.addressLine1,
                this.addressLine2,
                this.city,
                this.state,
                this.country,
                this.zipCode,
                this.phoneNumber
        );
    }
}

