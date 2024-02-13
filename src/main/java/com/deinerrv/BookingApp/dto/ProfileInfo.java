package com.deinerrv.BookingApp.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileInfo {
    @NotBlank(message = "name can't be blank")
    private String name;

    @NotBlank(message = "lastName can't be blank")
    private String lastName;

    @Nullable
    @Size(min = 8 , max = 8, message = "phone number's length must be 8 numbers")
    private String phone;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email is required")
    private String email; 
}
