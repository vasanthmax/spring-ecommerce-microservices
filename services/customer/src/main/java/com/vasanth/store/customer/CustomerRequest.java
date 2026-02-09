package com.vasanth.store.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer first name is required")
        String firstName,
        @NotNull(message = "Customer last name is required")
        String lastName,
        @NotNull(message = "Customer Email is required")
        @Email(message = "Email is not valid")
        String email,
        Address address
) {
}
