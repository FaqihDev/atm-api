package com.jamsirat.atmapi.service;

import com.jamsirat.atmapi.dto.request.CompleteOrUpdateUserProfileRequest;
import com.jamsirat.atmapi.dto.response.UserProfileDetailResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface IUserProfileService {

    UserProfileDetailResponse completeUserProfile(CompleteOrUpdateUserProfileRequest request);

    UserProfileDetailResponse updateUserProfile(CompleteOrUpdateUserProfileRequest request);

    UserProfileDetailResponse getDetailUserProfile(HttpServletRequest request);





}