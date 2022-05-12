package kr.or.cola.backend.config;

import kr.or.cola.backend.user.UserService;
import kr.or.cola.backend.security.OAuth2AuthenticationSuccessHandler;
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
//                .antMatchers("/**").permitAll()
                .antMatchers("/", "/css/**", "/images/**", "/js/**").permitAll()
                .antMatchers("/swagger*/**", "/v3/api-docs/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
            .and()
            .logout()
                .logoutSuccessUrl("/")
                .and()
            .oauth2Login()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .userInfoEndpoint()
                    .userService(userService);

    }
}
