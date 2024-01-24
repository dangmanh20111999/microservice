package com.manhnd.userservice.service.impl;

import com.manhnd.userservice.model.RefreshToken;
import com.manhnd.userservice.model.User;
import com.manhnd.userservice.repository.RefreshTokenRepository;
import com.manhnd.userservice.repository.UserRepository;
import com.manhnd.userservice.service.RefreshTokenService;
import com.manhnd.userservice.util.TokenRefreshException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

//    @Value("${app.jwtRefreshExpirationMs}")
    private static long refreshTokenDurationMs = 86400000L;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createRefreshToken(String ids) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setIds(userRepository.findUserByIds(ids).getIds());
        refreshToken.setExpirydate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public User findUserByRefreshToken(String refreshToken) {
        return userRepository.findUserByRefreshToken(refreshToken);
    }


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpirydate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

}
