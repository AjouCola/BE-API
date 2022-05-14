package kr.or.cola.backend.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AuthTokenRepository extends JpaRepository<AuthToken,String> {
    Optional<AuthToken> findByTokenAndExpirationDateAfterAndExpired(String token, LocalDateTime now, boolean expired);

}