package com.jamsirat.atmapi.dto.response;


import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDetailResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1386595506033311891L;
    private Long userId;
    private String fullName;
    private String birthPlace;
    private LocalDate birthDate;
    private String gender;
    private String height;
    private String address;
    private String phoneNumber;
    private String origin;
    private String kelompokSambung;
    private String desaSambung;
    private String kelompokAddress;
    private String desaAddress;
    private List <RoleResponseDTO> roles;
    private String progress;

}