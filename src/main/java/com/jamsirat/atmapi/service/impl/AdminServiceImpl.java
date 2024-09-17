package com.jamsirat.atmapi.service.impl;


import com.jamsirat.atmapi.base.PaginatedResponse;
import com.jamsirat.atmapi.base.PaginationUtil;
import com.jamsirat.atmapi.dto.response.UserProfileDetailResponse;
import com.jamsirat.atmapi.exception.DataNotFoundException;
import com.jamsirat.atmapi.exception.InvalidTokenException;
import com.jamsirat.atmapi.exception.UnauthorizedGrantingAccessException;
import com.jamsirat.atmapi.mapper.UserProfileDetailMapper;

import com.jamsirat.atmapi.mapper.UserProfileMapper;
import com.jamsirat.atmapi.model.auth.User;
import com.jamsirat.atmapi.model.profile.UserProfile;
import com.jamsirat.atmapi.repository.IDomicileRepository;
import com.jamsirat.atmapi.repository.IUserProfileExtendedRepository;
import com.jamsirat.atmapi.repository.IUserProfileRepository;
import com.jamsirat.atmapi.repository.IUserRepository;
import com.jamsirat.atmapi.statval.enumeration.EUserRole;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminServiceImpl  {

    private final IUserRepository userRepository;
    private final IUserProfileRepository userProfileRepository;
    private final IDomicileRepository domicileRepository;
    private final IUserProfileExtendedRepository userProfileExtendedRepository;
    private final UserProfileMapper userProfileDetailMapper;

    public PaginatedResponse<UserProfileDetailResponse> fetchAllUsers(Integer page, Integer size, HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Validate Authorization header
        if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            throw new InvalidTokenException("Invalid token", "Please check the token");
        }

        String token = authHeader.substring(7);
        User user;

        try {
            // Find user by token
            user = userRepository.findByToken(token);
            if (user == null) {
                throw new InvalidTokenException("Invalid token", "User not found");
            }

            // Check if user has ADMIN role
            boolean isAdmin = user.getRoles().stream()
                    .map(role -> role.getUserRole().getName())
                    .anyMatch(roleName -> roleName.equals(EUserRole.ADMIN.getName()));

            if (!isAdmin) {
                throw new UnauthorizedGrantingAccessException("Unauthorized role", "Limited scope and access");
            }

            // Default pagination values if not provided
            int defaultPage = (page == null || page < 1) ? 1 : page;
            int defaultSize = (size == null || size < 1) ? 10 : size;

            Pageable pageable = PageRequest.of(defaultPage - 1, defaultSize);
            Page<UserProfile> userProfilePage = userProfileRepository.findAll(pageable);

            // Map the list of UserProfile to UserProfileDetailResponse

             return PaginationUtil.toPaginationResponse(userProfilePage, userProfile ->  {

                var pUser = userRepository.findByUserProfile(userProfile);
                var userProfileExtended = userProfileExtendedRepository
                        .findByUserProfile(Optional.of(userProfile))
                        .orElseThrow(() -> new DataNotFoundException("Data user profile is missing", "Please contact administrator"));

                var domicile = domicileRepository.findByUserProfileExtendedId(Optional.of(userProfileExtended))
                        .orElseThrow(() -> new DataNotFoundException("Data domicile is missing", "Please contact administrator"));

                return userProfileDetailMapper.convert(new UserProfileMapper.Request(pUser,userProfile, userProfileExtended, domicile));
            });

        } catch (JwtException e) {
            throw new InvalidTokenException("Invalid JWT", "JWT token validation failed");
        }
    }

    public PaginatedResponse<UserProfileDetailResponse> filterBy(Integer page,
                                                                 Integer size,
                                                                 String desa,

                                                                 HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Validate Authorization header
        if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            throw new InvalidTokenException("Invalid token", "Please check the token");
        }

        String token = authHeader.substring(7);
        User user;

        try {
            // Find user by token
            user = userRepository.findByToken(token);
            if (user == null) {
                throw new InvalidTokenException("Invalid token", "User not found");
            }

            // Check if user has ADMIN role
            boolean isAdmin = user.getRoles().stream()
                    .map(role -> role.getUserRole().getName())
                    .anyMatch(roleName -> roleName.equals(EUserRole.ADMIN.getName()));

            if (!isAdmin) {
                throw new UnauthorizedGrantingAccessException("Unauthorized role", "Limited scope and access");
            }

            // Default pagination values if not provided
            int defaultPage = (page == null || page < 1) ? 1 : page;
            int defaultSize = (size == null || size < 1) ? 10 : size;

            Pageable pageable = PageRequest.of(defaultPage - 1, defaultSize);
            Page<UserProfile> userProfilePage = userProfileRepository.findAll(pageable);

            // Map the list of UserProfile to UserProfileDetailResponse

            return PaginationUtil.toPaginationResponse(userProfilePage, userProfile ->  {

                var pUser = userRepository.findByUserProfile(userProfile);
                var userProfileExtended = userProfileExtendedRepository
                        .findByUserProfile(Optional.of(userProfile))
                        .orElseThrow(() -> new DataNotFoundException("Data user profile is missing", "Please contact administrator"));

                var domicile = domicileRepository.findByUserProfileExtendedId(Optional.of(userProfileExtended))
                        .orElseThrow(() -> new DataNotFoundException("Data domicile is missing", "Please contact administrator"));

                return userProfileDetailMapper.convert(new UserProfileMapper.Request(pUser,userProfile, userProfileExtended, domicile));
            });

        } catch (JwtException e) {
            throw new InvalidTokenException("Invalid JWT", "JWT token validation failed");
        }
    }



}
