package co.com.bancolombia.api.security;

import co.com.bancolombia.secretconsumer.AzureSecuritySecret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@EnableWebFlux
@EnableWebFluxSecurity
@Slf4j
public class AzureSecurityConfig {

    @Autowired
    @Qualifier("AzureSecuritySecret")
    private AzureSecuritySecret azureSecurity;

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf().disable()
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/actuator/health", "/ospo-metrics/openapi/**", "/api/github/**", "/api/get-azure-token")
                        .permitAll()
                        .pathMatchers("/api/**")
                        .authenticated())
                .oauth2ResourceServer()
                .jwt()
                .jwtDecoder(jwtDecoder())
                .jwtAuthenticationConverter(grantedAuthoritiesExtractor())
        ;
        return http.build();
    }

    ReactiveJwtDecoder jwtDecoder() {
        NimbusReactiveJwtDecoder jwtDecoder = (NimbusReactiveJwtDecoder)
                ReactiveJwtDecoders.fromIssuerLocation(azureSecurity.getJwtIssuerUri());

        OAuth2TokenValidator<Jwt> tokenValidator = new DelegatingOAuth2TokenValidator<Jwt>(
                JwtValidators.createDefaultWithIssuer(azureSecurity.getJwtIssuerIss()),
                new JwtClaimValidator<List<String>>("aud", (aud) -> {
                    log.debug("Validating JWT token, {} with {}", aud, azureSecurity.getClientId());
                    return aud != null && (aud.contains("api://"+azureSecurity.getClientId()) || aud.contains(azureSecurity.getAudience()));
                }));

        jwtDecoder.setJwtValidator(tokenValidator);

        return jwtDecoder;
    }

    Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter
                ((jwt) -> {
                    log.info("Validating roles: " + jwt.getAudience().toString());
                    Collection<?> authorities = (Collection<?>)
                            jwt.getClaims().getOrDefault("roles", Collections.emptyList());

                    return authorities.stream()
                            .map(Object::toString)
                            .map(role -> {
                                log.info("Role: " + role);
                                return "ROLE_".concat(role);
                            })
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
                });
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
