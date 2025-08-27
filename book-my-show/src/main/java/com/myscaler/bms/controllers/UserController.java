package com.myscaler.bms.controllers;

import com.myscaler.bms.dtos.*;
import com.myscaler.bms.models.User;
import com.myscaler.bms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public SignupUserResponseDTO signupUser(SignupUserRequestDTO requestDTO){
        SignupUserResponseDTO responseDTO = new SignupUserResponseDTO();
        try {
            User user = this.userService.signupUser(requestDTO.getName(), requestDTO.getEmail(), requestDTO.getPassword());
            responseDTO.setName(user.getName());
            responseDTO.setEmail(user.getEmail());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }

    public LoginResponseDto login(LoginRequestDto requestDto){
        LoginResponseDto responseDto = new LoginResponseDto();
        try {
            responseDto.setLoggedIn(
                    this.userService.login(requestDto.getEmail(), requestDto.getPassword()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            responseDto.setLoggedIn(false);
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

}
