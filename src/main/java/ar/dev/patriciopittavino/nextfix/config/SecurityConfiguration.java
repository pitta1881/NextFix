package ar.dev.patriciopittavino.nextfix.config;

import ar.dev.patriciopittavino.nextfix.service.UserCustomService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final UserCustomService userCustomService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csfr -> csfr.ignoringRequestMatchers(toH2Console()))
                .userDetailsService(userCustomService)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/", "/register").permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/backoffice")).hasAuthority("ROLE_ADMIN")
                        .requestMatchers(toH2Console()).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/login").permitAll().defaultSuccessUrl("/", true)
                )
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }

}
