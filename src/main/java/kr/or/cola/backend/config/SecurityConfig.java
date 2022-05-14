package kr.or.cola.backend.config;

import kr.or.cola.backend.oauth.OAuth2AuthenticationSuccessHandler;
import kr.or.cola.backend.user.UserService;
import kr.or.cola.backend.user.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Value("${cola.redirect-uri.sign-in}")
    private String signInUrl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
                .and()
            .csrf()
                .disable()
            .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers("/", "/css/**", "/images/**", "/js/**").permitAll()
                .antMatchers("/swagger*/**", "/v3/api-docs/**").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
            .and()
            .logout()
                .logoutSuccessUrl("/")
                .and()
            .oauth2Login()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(failureHandler())
                .userInfoEndpoint()
                    .userService(userService);

    }

    @Bean
    SimpleUrlAuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler(signInUrl);
    }
}
