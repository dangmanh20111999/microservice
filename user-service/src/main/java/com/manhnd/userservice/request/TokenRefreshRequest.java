package com.manhnd.userservice.request;

import lombok.Data;

@Data
public class TokenRefreshRequest {
    private String refreshToken;
}
