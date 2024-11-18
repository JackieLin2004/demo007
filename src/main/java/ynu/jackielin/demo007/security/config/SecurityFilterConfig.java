package ynu.jackielin.demo007.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
@EnableMethodSecurity
public class SecurityFilterConfig {

    @Bean
    SecurityFilterChain apiSecurityfilterChain(HttpSecurity http) throws Exception {
        // 公开的API路径，不需要经过授权
        var publicPaths = new String[]{"/doc/**", "/auth/**", "/captcha/**"};

        http.authorizeHttpRequests(authorize ->
                authorize.requestMatchers(publicPaths).permitAll()  // 允许访问
                        .anyRequest().authenticated()   // 需要授权才能访问
        );

        http.oauth2ResourceServer(server->{
            server.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(getJwtConverter()));
        });

        disableUnusedFilter(http);
        // build相当于构建者模式
        return http.build();
    }

    /** 用于创建一个jwt信息的转换器 */
    private Converter<Jwt, AbstractAuthenticationToken> getJwtConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // 将 jwt 中的 roles 信息转换成权限对象
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {

            Collection<String> authorities = jwt.getClaimAsStringList("roles");
            System.out.printf("roles: %s\n", authorities);

            return authorities.stream()
                    .map(r->new SimpleGrantedAuthority("ROLE_"+ r))
                    .collect(Collectors.toList());

        });

        return converter;
    }

    /** 对于无状态的RESTFul风格的应用，需要禁用掉这些被默认启用的过滤器 */
    private void disableUnusedFilter(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .anonymous(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .requestCache(RequestCacheConfigurer::disable);
    }

}
