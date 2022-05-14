package kr.or.cola.backend.auth;

import kr.or.cola.backend.exception.BadRequestException;
import kr.or.cola.backend.exception.EnvironmentCode;
import kr.or.cola.backend.user.domain.User;
import kr.or.cola.backend.user.domain.UserRepository;
import kr.or.cola.backend.util.CustomMailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthTokenService {
    private final AuthTokenRepository authTokenRepository;
    private final UserRepository userRepository;
    private final CustomMailSender customMailSender;

    public void sendAuthMail(Long userId, String targetEmail) {
        String token = createEmailAuthToken(userId);
        customMailSender.sendAuthenticationToken(token, targetEmail);
    }

    /**
     * 이메일 인증 토큰 생성
     * @return token
     */
    private String createEmailAuthToken(Long userId){

        Assert.hasText(userId.toString(),"userId는 필수 입니다.");

        AuthToken authToken = AuthToken.createEmailConfirmationToken(userId);
        authTokenRepository.save(authToken);

        return authToken.getToken();
    }

    /**
     * 유효한 토큰 가져오기
     * @param token
     * @return
     */
    public AuthToken findByTokenAndExpirationDateAfterAndExpired(String token){
        Optional<AuthToken> confirmationToken = authTokenRepository.findByTokenAndExpirationDateAfterAndExpired(token, LocalDateTime.now(),false);
        return confirmationToken.orElseThrow(()-> new BadRequestException(EnvironmentCode.TOKEN_NOT_FOUND.name()));
    };

    /**
     * 이메일 인증 로직
     * @param token
     */
    public String confirmEmail(String token) {
        AuthToken findAuthToken = findByTokenAndExpirationDateAfterAndExpired(token);
        User user = userRepository.findById(findAuthToken.getUserId())
                .orElseThrow(() -> new RuntimeException("invalid user id"));
        findAuthToken.useToken();	// 토큰 만료 로직을 구현해주면 된다. ex) expired 값을 true로 변경
//        user.emailVerifiedSuccess();	// 유저의 이메일 인증 값 변경 로직을 구현해주면 된다. ex) emailVerified 값을 true로 변경
//        userRepository.save(user);
        
        return "성공 짝짝";
    }

}