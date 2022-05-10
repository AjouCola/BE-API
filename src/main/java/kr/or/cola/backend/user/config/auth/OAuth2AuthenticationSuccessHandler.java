package kr.or.cola.backend.user.config.auth;

import kr.or.cola.backend.user.UserService;
import kr.or.cola.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static kr.or.cola.backend.user.domain.Role.USER;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserService customOAuth2UserService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        if(session != null){
            DefaultOAuth2User principal = (DefaultOAuth2User)authentication.getPrincipal();

            User user = customOAuth2UserService.findByEmail((String)principal.getAttributes().get("email"));
            if (user.getRole() == USER) {
                // 메인 페이지로 리다이렉션
                String redirectUrl = (String) session.getAttribute("redirect");
                if (redirectUrl != null) {
                    session.removeAttribute("redirect");
                    getRedirectStrategy().sendRedirect(request, response, redirectUrl);
                }
            }
            else {  // 신규 로그인
                String signUpUrl = "http://localhost:3000/signUp";
                getRedirectStrategy().sendRedirect(request, response, signUpUrl);
            }
        }
        else {  // 회원 아님 =>
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        return "http://localhost:3000";
    }

}
