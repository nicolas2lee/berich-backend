
package tao.berich.gateway.security.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class Oauth2Config {
    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/welcome").permitAll()
                .anyExchange().authenticated().and().oauth2Login()
                ;
        return http.build();
    }
}

