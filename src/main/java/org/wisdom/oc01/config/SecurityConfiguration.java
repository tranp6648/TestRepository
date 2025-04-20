package org.wisdom.oc01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.wisdom.oc01.service.AccountService;
import org.wisdom.oc01.service.impl.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity // Kích hoạt bảo mật Web
@EnableMethodSecurity // Kích hoạt bảo mật theo phương thức
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;
    private final AccountService accountService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfiguration(@Lazy JwtFilter jwtFilter,
                                 @Lazy AccountService accountService,
                                 OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler,
                                 CustomOAuth2UserService customOAuth2UserService) {
        this.jwtFilter = jwtFilter;
        this.accountService = accountService;
        this.oAuth2LoginSuccessHandler = oAuth2LoginSuccessHandler;
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // ✅ Correct
                .csrf(csrf -> csrf.disable()) // Tắt CSRF vì dùng JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Không dùng session
                .authorizeHttpRequests(auth -> auth
                        // Anonymous: Không cần token
                        .requestMatchers(HttpMethod.GET, APIURL.URL_ANONYMOUS_GET).permitAll()
                        .requestMatchers(HttpMethod.POST, APIURL.URL_ANONYMOUS_POST).permitAll()
                        .requestMatchers(HttpMethod.PUT, APIURL.URL_ANONYMOUS_PUT).permitAll()
                        .requestMatchers(HttpMethod.DELETE, APIURL.URL_ANONYMOUS_DELETE).permitAll()

                        // Role USER - Với RoleHierarchy, ADMIN và COMPANY cũng có quyền truy cập
                        .requestMatchers(HttpMethod.GET, APIURL.URL_USER_GET).hasRole("USER")
                        .requestMatchers(HttpMethod.POST, APIURL.URL_USER_POST).hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, APIURL.URL_USER_PUT).hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, APIURL.URL_USER_DELETE).hasRole("USER")

                        // Role COMPANY - Với RoleHierarchy, ADMIN cũng có quyền truy cập
                        .requestMatchers(HttpMethod.GET, APIURL.URL_STUDENT_GET).hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST, APIURL.URL_STUDENT_POST).hasRole("STUDENT")
                        .requestMatchers(HttpMethod.PUT, APIURL.URL_STUDENT_PUT).hasRole("STUDENT")
                        .requestMatchers(HttpMethod.DELETE, APIURL.URL_STUDENT_DELETE).hasRole("STUDENT")

                        // Role ADMIN
                        .requestMatchers(HttpMethod.GET, APIURL.URL_ADMIN_GET).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, APIURL.URL_ADMIN_POST).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, APIURL.URL_ADMIN_PUT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, APIURL.URL_ADMIN_DELETE).hasRole("ADMIN")

                        // Tất cả các request khác yêu cầu xác thực
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider()) // Provider xác thực
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Thêm JwtFilter
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(accountService); // Sử dụng service quản lý user
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // Sử dụng mã hóa mật khẩu
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Mã hóa mật khẩu bằng BCrypt
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager(); // Quản lý xác thực
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:5173"); // Allow frontend access
        corsConfiguration.addAllowedHeader("*"); // Allow all headers
        corsConfiguration.addAllowedMethod("*"); // Allow all HTTP methods
        corsConfiguration.setAllowCredentials(true); // Allow authentication cookies

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }


    @Bean
    public OidcUserService oidcUserService1() {
        return new OidcUserService(); // Xử lý thông tin người dùng OIDC
    }

    @Bean
    public DefaultOAuth2UserService oAuth2UserService1() {
        return new DefaultOAuth2UserService(); // Xử lý thông tin người dùng OAuth2
    }
}
