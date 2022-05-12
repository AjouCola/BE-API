package kr.or.cola.backend.user;

import kr.or.cola.backend.mail.auth.AuthToken;
import kr.or.cola.backend.mail.auth.AuthTokenService;
import kr.or.cola.backend.oauth.dto.OAuthAttributes;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.user.domain.User;
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
    private final AuthTokenService authTokenService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElse(null);
    }

    /**
     * 이메일 인증 로직
     * @param token
     */
    public String confirmEmail(String token) {
        AuthToken findAuthToken = authTokenService.findByTokenAndExpirationDateAfterAndExpired(token);
        User user = userRepository.findById(findAuthToken.getUserId())
            .orElseThrow(() -> new RuntimeException("invalid user id"));
        findAuthToken.useToken();	// 토큰 만료 로직을 구현해주면 된다. ex) expired 값을 true로 변경
        user.emailVerifiedSuccess();	// 유저의 이메일 인증 값 변경 로직을 구현해주면 된다. ex) emailVerified 값을 true로 변경
        return "성공 짝짝";
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
            .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
