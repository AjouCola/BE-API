package kr.or.cola.backend.user.config.auth;

import kr.or.cola.backend.user.UserService;
import kr.or.cola.backend.user.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**/*").permitAll()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .antMatchers("/**").permitAll()
//                .antMatchers("/", "/css/**", "/images/**", "/js/**").permitAll()
//                .anyRequest().authenticated()
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(userService)
            .and()
            .successHandler(oAuth2AuthenticationSuccessHandler);
    }
}
