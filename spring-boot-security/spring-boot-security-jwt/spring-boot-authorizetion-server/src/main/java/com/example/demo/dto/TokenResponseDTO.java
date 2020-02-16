package com.example.demo.dto;

public class TokenResponseDTO {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenResponseDTO convert(String token) {
        TokenResponseDTO tokenResponseDTO =  new TokenResponseDTO();
        tokenResponseDTO.setToken(token);

        return tokenResponseDTO;
    }
}
