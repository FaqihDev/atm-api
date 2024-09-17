package com.jamsirat.atmapi.model.profile;

import com.jamsirat.atmapi.model.Base.AAuditableBase;
import com.jamsirat.atmapi.model.Progress;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import com.jamsirat.atmapi.statval.enumeration.EGender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.ExceptionMessage;

@Entity
@Table(name = "user_profile_extended")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserProfileExtended extends AAuditableBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 5773885358142110306L;


    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Email(message = ExceptionMessage.EMAIL_VALID_FORMAT)
    @NotBlank(message = ExceptionMessage.EMAIL_NOT_NULL)
    @Column(name = "email")
    private String email;

    @Column(name = "origin")
    private String origin;

    @Column(name = "height")
    private String height;

    @Column(name = "image")
    private String image;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile")
    private UserProfile userProfile;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "progress_id")
    private Progress progress;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;



}