package com.jamsirat.atmapi.endpoint;



import com.jamsirat.atmapi.base.PaginatedResponse;
import com.jamsirat.atmapi.dto.request.CompleteOrUpdateUserProfileRequest;
import com.jamsirat.atmapi.dto.response.HttpResponse;
import com.jamsirat.atmapi.dto.response.UserProfileDetailResponse;
import com.jamsirat.atmapi.service.IUserProfileService;
import com.jamsirat.atmapi.service.impl.AdminServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.jamsirat.atmapi.statval.constant.IApplicationConstant.ContextPath.USER_PROFILE;
import static com.jamsirat.atmapi.statval.constant.IApplicationConstant.Path.User.*;
import static com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.DeveloperSuccessMessage;
import static com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.SuccessMessage;


@RestController
@RequestMapping(USER_PROFILE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserProfileEndpoint {


    private final IUserProfileService userProfileService;
    private final AdminServiceImpl adminService;

    @PostMapping(COMPLETE_PROFILE)
    public ResponseEntity<?> completeProfile(@RequestBody CompleteOrUpdateUserProfileRequest request) {
        UserProfileDetailResponse data = userProfileService.completeUserProfile(request);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .developerMessage("Created user profile successfully")
                        .message("Data saved")
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(data)
                        .build()
        );
    }

    @PutMapping(UPDATE_PROFILE)
    public ResponseEntity<?> updateProfile(@RequestBody CompleteOrUpdateUserProfileRequest request) {
        UserProfileDetailResponse data = userProfileService.updateUserProfile(request);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .developerMessage("Userprofile updated successfully")
                        .message("Data Updated")
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(data)
                        .build()
        );
    }

    @GetMapping(GET_DETAIL_PROFILE)
    public HttpResponse<?> getDetailProfile(HttpServletRequest request) {
        UserProfileDetailResponse data = userProfileService.getDetailUserProfile(request);
        HttpResponse<?> httpResponse = HttpResponse.builder()
                .developerMessage("Userprofile retrieved successfully")
                .message("User Profile")
                .timeStamp(LocalDateTime.now().toString())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(data)
                .build();
                return HttpResponse.build(SuccessMessage.DATA_FETCH_SUCCESSFULLY,DeveloperSuccessMessage.DATA_FETCH_SUCCESSFULLY,HttpStatus.OK,data);
    }


    @GetMapping(FETCH_ALL_USERS)
    public HttpResponse<PaginatedResponse<UserProfileDetailResponse>> fetchAllUsers(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            HttpServletRequest request) {
            PaginatedResponse<UserProfileDetailResponse> data = adminService.fetchAllUsers(page, size, request);
            if (Objects.nonNull(data) && !data.getData().isEmpty()) {
                return HttpResponse.build(SuccessMessage.DATA_FETCH_SUCCESSFULLY,DeveloperSuccessMessage.DATA_FETCH_SUCCESSFULLY,HttpStatus.OK,data);
            }
                return HttpResponse.noContent();
            }

}