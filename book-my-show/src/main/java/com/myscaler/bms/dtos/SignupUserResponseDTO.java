package com.myscaler.bms.dtos;

import lombok.Data;

@Data
public class SignupUserResponseDTO {
    private ResponseStatus responseStatus;
    private String name;
    private String email;
    private int userId;
}
