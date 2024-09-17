package com.jamsirat.atmapi.mapper;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.UserProfileDetailResponse;
import com.jamsirat.atmapi.model.profile.Domicile;
import com.jamsirat.atmapi.model.profile.UserProfile;
import com.jamsirat.atmapi.model.profile.UserProfileExtended;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class UserProfileDetailMapper extends ADATAMapper<UserProfileDetailMapper.Request, UserProfileDetailResponse> {


    @Override
    public UserProfileDetailResponse convert(UserProfileDetailMapper.Request request) {
        return UserProfileDetailResponse.builder()
                .userId(request.userProfile.getUser().getId())
                .fullName(request.userProfile.getUser().getFirstName() + " " + request.userProfile.getUser().getLastName())
                .address(request.userProfileExtended.getAddress())
                .birthDate(request.userProfileExtended.getBirthDate())
                .birthPlace(request.userProfileExtended.getBirthPlace())
                .gender(request.userProfileExtended.getGender().getName())
                .origin(request.userProfileExtended.getOrigin())
                .phoneNumber(request.userProfileExtended.getPhoneNumber())
                .kelompokSambung(request.domicile.getKelompokSambung())
                .kelompokAddress(request.domicile.getKelompokAddress())
                .desaSambung(request.domicile.getDesaSambung())
                .desaAddress(request.domicile.getDesaAddress())
                .height(request.userProfileExtended.getHeight())
                .build();

    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Request {
        public UserProfile userProfile;
        public UserProfileExtended userProfileExtended;
        public Domicile domicile;
    }
}
