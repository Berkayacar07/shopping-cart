package com.example.shoppingcart.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    public ApiResponse(String message) {
        this.message = message;
    }
}

