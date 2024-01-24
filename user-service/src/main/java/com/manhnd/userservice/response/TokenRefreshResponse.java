package com.manhnd.userservice.response;

import lombok.Data;

@Data
public class TokenRefreshResponse {
    private String refreshToken;
    private String token;

    public TokenRefreshResponse(String refreshToken, String token) {
        this.refreshToken = refreshToken;
        this.token = token;
    }
}
