package com.deinerrv.BookingApp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    
    @NotBlank
    private String token;

    @NotBlank
    private String refreshToken;
}
