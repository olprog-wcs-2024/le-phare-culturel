package fr.olprog_c.le_phare_culturel.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.olprog_c.le_phare_culturel.entities.BlacklistedToken;
import fr.olprog_c.le_phare_culturel.repositories.BlacklistedTokenRepository;

@Service
public class TokenBlacklistService {

    @Autowired
    private BlacklistedTokenRepository blacklistedTokenRepository;

    public void blacklistToken(String token, Instant expiryDate) {
        BlacklistedToken blacklistedToken = new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedToken.setExpiryDate(expiryDate);
        blacklistedTokenRepository.save(blacklistedToken);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokenRepository.findByToken(token)
                .map(blacklistedToken -> blacklistedToken.getExpiryDate().isAfter(Instant.now()))
                .orElse(false);
    }
}

