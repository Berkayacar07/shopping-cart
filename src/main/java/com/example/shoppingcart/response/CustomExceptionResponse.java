package com.example.shoppingcart.response;

import java.util.Date;
import lombok.*;

@Getter
@Setter
public class CustomExceptionResponse {
    private String message;
    private Date timestamp;
}