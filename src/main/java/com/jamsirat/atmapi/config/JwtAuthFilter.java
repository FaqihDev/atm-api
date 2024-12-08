package com.jamsirat.atmapi.config;

import com.jamsirat.atmapi.exception.TokenAlreadyExpiredException;
import com.jamsirat.atmapi.exception.UnauthorizedGrantingAccessException;
import com.jamsirat.atmapi.repository.ITokenRepository;
import com.jamsirat.atmapi.service.impl.AccessControlServiceImpl;
import com.jamsirat.atmapi.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.DeveloperExceptionMessage;

import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.ExceptionMessage;
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final AccessControlServiceImpl accessControlService;

    private final ITokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest,
                                    @NonNull HttpServletResponse httpServletResponse,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        //check if the url is authentication
        if (httpServletRequest.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        //check if the header contain Authorization and extract username
        String AUTHORIZATION_HEADER = "Authorization";
        final String authHeader = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        String jwt = null;
        String userEmail = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
        } else {
            log.info("Missing or Invalid Authorization header");
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        //extract username and validate token
        userEmail = jwtService.extractUsername(jwt);
        if (userEmail == null || !jwtService.isTokenValid(jwt, userDetailsService.loadUserByUsername(userEmail))) {
            log.info("Invalid token or expired JWT Token");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new TokenAlreadyExpiredException(ExceptionMessage.TOKEN_IS_INVALID,DeveloperExceptionMessage.TOKEN_IS_INVALID);
        }

        //load user details and extract roles
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        String roles = jwtService.extractRoles(jwt);

        if (Objects.isNull(roles)) {
            log.info("No roles found in JWT Token");
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        //Convert roles to simpleGrantedAuthority objects
        List<SimpleGrantedAuthority> authorities = Arrays.stream(roles.split(",")).
                map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        String endpoint = httpServletRequest.getRequestURI();
        if (!accessControlService.hasAccess(userEmail,endpoint))  {
            log.info("User '{}' does not have access to '{}'",userEmail,endpoint);
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            throw new UnauthorizedGrantingAccessException(ExceptionMessage.ACCESS_DENIED,DeveloperExceptionMessage.ACCESS_DENIED);
        }

        //Successful authentication and set SecurityContext
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }
}