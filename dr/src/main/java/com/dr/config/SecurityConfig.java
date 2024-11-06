package com.dr.config;

import com.dr.domain.CustomOAuth2User;
import com.dr.service.user.CustomOAuth2UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    // 카카오로 로그아웃을 넘겨버림으로써 완전히 로그아웃 가능하도록 구현.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(config -> config.anyRequest().permitAll());
        http.oauth2Login(oauth2Login -> oauth2Login
                .loginPage("/user/login")
                .userInfoEndpoint(userInfo -> userInfo
                        .userService(this.customOAuth2UserService)
                )
                .successHandler(this.successHandler())
        ).logout(logout -> logout
                .logoutSuccessHandler((request, response, authentication) -> {
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.invalidate(); // 세션 무효화
                    }
                    // 카카오 로그아웃 URL로 리다이렉트
                    String clientId = "abf507166ba07ff6370d66306018dbcd";
                    String logoutRedirectUri = "http://localhost:9000/main";
                    String kakaoLogoutUrl = "https://kauth.kakao.com/oauth/logout?client_id=" +
                            clientId + "&logout_redirect_uri=" + logoutRedirectUri;
                    response.sendRedirect(kakaoLogoutUrl);
                })
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "loginId") // 적절한 쿠키 삭제
                .clearAuthentication(true)
        );
        return http.build();
    }




    // 로그인 성공 핸들러
    @Bean
    public AuthenticationSuccessHandler successHandler() {

        return (request, response, authentication) -> {
            // 로그인 성공 후 리다이렉션할 경로 지정
            HttpSession session = request.getSession();
            session.setAttribute("loginId", ((CustomOAuth2User)(authentication.getPrincipal())).getProviderId());
            response.sendRedirect("/DRmain");
        };
    }


}

