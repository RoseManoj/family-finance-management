package com.example.family_finance_management.user;

public interface UserService {
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO);
}