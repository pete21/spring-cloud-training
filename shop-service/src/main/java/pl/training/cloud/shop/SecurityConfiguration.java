package pl.training.cloud.shop;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.http.HttpHeaders.LOCATION;

@KeycloakConfiguration
public class SecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder  authenticationManagerBuilder) {
        var keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        authenticationManagerBuilder.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader(LOCATION);
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/actuator/**").permitAll()
                .mvcMatchers("/**").hasRole("cloud");
    }

}
