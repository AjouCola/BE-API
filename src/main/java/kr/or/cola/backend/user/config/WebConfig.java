package kr.or.cola.backend.user.config;

import kr.or.cola.backend.user.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//            .allowedMethods("*")
            .allowedOrigins("http://localhost:8080", "http://localhost:3000")
            .allowedHeaders("*")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
            .allowCredentials(true)
//            .exposedHeaders("authorization")
            .maxAge(3600)
//        registry.addMapping("/**").allowedOrigins(
////            "http://localhost:3000", "http://52.79.77.93:3000", "http://cola.or.kr:3000",
////            "http://ec2-3-39-4-189.ap-northeast-2.compute.amazonaws.com:8080"
//        );
        ;

    }

}
