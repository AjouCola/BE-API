package kr.or.cola.backend.oauth;

import kr.or.cola.backend.user.UserService;
import kr.or.cola.backend.user.domain.Role;
import kr.or.cola.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserService userService;

    @Value("${cola.redirect-uri.sign-up}")
    private String signUpUrl;

    @Value("${cola.redirect-uri.main}")
    private String mainUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {

        DefaultOAuth2User principal = (DefaultOAuth2User)authentication.getPrincipal();
        User user = userService.findUserByEmail((String)principal.getAttributes().get("email"));

        if (user.getRole() == Role.GUEST) {  // 신규 로그인
            getRedirectStrategy().sendRedirect(request, response, signUpUrl);
        }
        else  if (user.getRole() == Role.USER) {
            getRedirectStrategy().sendRedirect(request, response, mainUrl);
        }

        clearAuthenticationAttributes(request);
    }
}
