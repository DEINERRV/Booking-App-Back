package com.deinerrv.BookingApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfile {
    
    @NotBlank(message = "name can't be blank")
    private String name;

    @NotBlank(message = "lastName can't be blank")
    private String lastName;

    @Size(min = 8 , max = 8, message = "phone number's length must be 8 numbers")
    private String phone;
}
