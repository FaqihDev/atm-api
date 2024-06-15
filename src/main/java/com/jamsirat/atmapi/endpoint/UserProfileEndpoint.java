package com.jamsirat.atmapi.endpoint;


import com.jamsirat.atmapi.dto.request.CompleteOrUpdateUserProfileRequest;
import com.jamsirat.atmapi.dto.response.CompleteUserProfileResponse;
import com.jamsirat.atmapi.dto.response.HttpResponse;
import com.jamsirat.atmapi.service.IUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;

import static com.jamsirat.atmapi.statval.constant.IApplicationConstant.ContextPath.USER_PROFILE;
import static com.jamsirat.atmapi.statval.constant.IApplicationConstant.Path.User.COMPLETE_PROFILE;

@RestController
@RequestMapping(USER_PROFILE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserProfileEndpoint {


    private final IUserProfileService userProfileService;

    @PostMapping(COMPLETE_PROFILE)
    public ResponseEntity<?> completeProfile(@RequestBody CompleteOrUpdateUserProfileRequest request) {
        CompleteUserProfileResponse data = userProfileService.completeUserProfile(request);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .developerMessage("Created user profile successfull")
                        .message("Data saved")
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(data)
                        .build()
        );
    }

}