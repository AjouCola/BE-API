package kr.or.cola.backend.auth;

import kr.or.cola.backend.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="auth_token")
public class AuthToken extends BaseTimeEntity {

    private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;	//토큰 만료 시간

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36)
    private String token;

    @Column(name="exiration_at")
    private LocalDateTime expirationDate;

    @Column(name="is_expierd")
    private boolean expired;

    @Column(name="user_id")
    private Long userId;

    /**
     * 이메일 인증 토큰 생성
     * @param userId
     * @return
     */
    public static AuthToken createEmailConfirmationToken(Long userId){
        AuthToken authToken = new AuthToken();
        authToken.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE); // 5분후 만료
        authToken.userId = userId;
        authToken.expired = false;
        return authToken;
    }

    /**
     * 토큰 사용으로 인한 만료
     */
    public void useToken(){
        expired = true;
    }
}
