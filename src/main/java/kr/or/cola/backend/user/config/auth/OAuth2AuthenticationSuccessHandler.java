package kr.or.cola.backend.user.config.auth;

import kr.or.cola.backend.user.config.auth.dto.CustomOAuth2UserService;
import kr.or.cola.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kr.or.cola.backend.user.domain.Role.ADMIN;
import static kr.or.cola.backend.user.domain.Role.USER;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private CustomOAuth2UserService customOAuth2UserService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        User user = customOAuth2UserService.findByEmail(((User)authentication.getPrincipal()).getEmail());
        if(user != null){
            if (user.getRole() == USER) {
                // 메인 페이지로 리다이렉션

            }
            else if (user.getRole() == ADMIN) {
                // 관리자 페이지로 리다이렉션

            }
            else {  // 로그인 실패

            }

        }
        else {  // 회원 아님 =>

        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        return "http://localhost:3000";
    }

}
