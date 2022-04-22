package kr.or.cola.backend.user;

import kr.or.cola.backend.mail.auth.AuthToken;
import kr.or.cola.backend.mail.auth.AuthTokenService;
import kr.or.cola.backend.user.config.auth.dto.OAuthAttributes;
import kr.or.cola.backend.user.config.auth.dto.SessionUser;
import kr.or.cola.backend.user.domain.User;
import kr.or.cola.backend.userinfo.domain.UserInfo;
import kr.or.cola.backend.userinfo.domain.UserInfoRepository;
import kr.or.cola.backend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private  final UserRepository userRepository;
    private final HttpSession httpSession;
    private final UserInfoRepository userInfoRepository;
    private final AuthTokenService authTokenService;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        if(!userInfoRepository.existsByUserId(user.getId())) {
            // 회원가입 페이지로 리다이렉션
        } else {
            // 메인 페이지로 리다이렉션
        }

        httpSession.setAttribute("user", new SessionUser(user));



        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
    /**
     * 이메일 인증 로직
     * @param token
     */
    public String confirmEmail(String token) {
        AuthToken findAuthToken = authTokenService.findByTokenAndExpirationDateAfterAndExpired(token);
        UserInfo findUserInfo = userInfoRepository.findByUserId(findAuthToken.getUserId());
        findAuthToken.useToken();	// 토큰 만료 로직을 구현해주면 된다. ex) expired 값을 true로 변경
        findUserInfo.emailVerifiedSuccess();	// 유저의 이메일 인증 값 변경 로직을 구현해주면 된다. ex) emailVerified 값을 true로 변경
        return "성공 짝짝";
    }
}
