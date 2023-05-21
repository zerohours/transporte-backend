package com.brizuela.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthorizationFilters extends BasicAuthenticationFilter {

    private final static String TOKEN_PREFIX = "Bearer ";
    private final static String AUTH_HEADER_NAME = "Authorization";
    private final static String ACCESS_TOKEN_PARAM_NAME = "access_token";

    @Value("${app.security.jwt.secret}")
    private String accessTokenSecret;

    public JWTAuthorizationFilters(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * Método para descifrar el token enviado por el cliente
     * y para establecer el usuario de la sesión
     *
     * @param req   la solicitud
     * @param res   la respuesta
     * @param chain objeto para continuar al siguiente filtro
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String token = req.getHeader(AUTH_HEADER_NAME);
        if (token == null) {
            token = req.getParameter(ACCESS_TOKEN_PARAM_NAME);
        }

        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    /**
     * Método para obtener el token que representa la autenticación
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (token != null) {
            try {
                // parse the token.
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(accessTokenSecret.getBytes())
                        .build()
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();

                String user = claims.getSubject();
                Object authoritiesObject = claims.get("authorities");
                List<String> authorities;

                if (authoritiesObject instanceof List) {
                    authorities = (List<String>) authoritiesObject;
                } else {
                    authorities = Collections.emptyList();
                }
                List<GrantedAuthority> grantedAuthorityList = authorities
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorityList);
                }
            } catch (JwtException e) {
                return null;
            }
            return null;
        }
        return null;
    }
}
