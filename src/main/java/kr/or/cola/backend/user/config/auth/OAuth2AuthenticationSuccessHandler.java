package kr.or.cola.backend.user.config.auth;

import kr.or.cola.backend.user.UserService;
import kr.or.cola.backend.user.domain.Role;
import kr.or.cola.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kr.or.cola.backend.user.domain.Role.USER;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserService customOAuth2UserService;

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        DefaultOAuth2User principal = (DefaultOAuth2User)authentication.getPrincipal();

        User user = customOAuth2UserService.findByEmail((String)principal.getAttributes().get("email"));

        if (user.getRole() == Role.GUEST) {  // 신규 로그인
            String signUpUrl = "http://localhost:3000/signUp";
            getRedirectStrategy().sendRedirect(request, response, signUpUrl);
        }
        else  if (user.getRole() == USER) {
            resultRedirectStrategy(request, response, authentication);
        }
        else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

    protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
                                          Authentication authentication) throws IOException, ServletException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if(savedRequest!=null) {
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            redirectStrategy.sendRedirect(request, response, "http://localhost:3000");
        }
    }
}
