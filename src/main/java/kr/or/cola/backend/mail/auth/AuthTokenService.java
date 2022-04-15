package kr.or.cola.backend.mail.auth;

import kr.or.cola.backend.exception.BadRequestException;
import kr.or.cola.backend.exception.EnvironmentCode;
import kr.or.cola.backend.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthTokenService {
    private final AuthTokenRepository confirmationTokenRepository;
    private final MailService mailService;
    /**
     * 이메일 인증 토큰 생성
     * @return
     */
    public String createEmailAuthToken(Long userId, String receiverEmail){

        Assert.hasText(userId.toString(),"userId는 필수 입니다.");
        Assert.hasText(receiverEmail,"receiverEmail은 필수 입니다.");

        AuthToken emailConfirmationToken = AuthToken.createEmailConfirmationToken(userId);
        confirmationTokenRepository.save(emailConfirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiverEmail);
        mailMessage.setSubject("회원가입 이메일 인증");
        mailMessage.setText(emailConfirmationToken.getToken());
        mailService.sendEmail(mailMessage);

        return emailConfirmationToken.getToken();
    }

    /**
     * 유효한 토큰 가져오기
     * @param token
     * @return
     */
    public AuthToken findByTokenAndExpirationDateAfterAndExpired(String token){
        Optional<AuthToken> confirmationToken = confirmationTokenRepository.findByTokenAndExpirationDateAfterAndExpired(token, LocalDateTime.now(),false);
        return confirmationToken.orElseThrow(()-> new BadRequestException(EnvironmentCode.TOKEN_NOT_FOUND.name()));
    };

}