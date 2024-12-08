package com.jamsirat.atmapi.statval.enumeration;


import lombok.Getter;

@Getter
public enum EUserRole {
    USER("USER"),
    ADMIN("ADMIN"),
    PRINCIPLE("PRINCIPLE"),
    DEVELOPER("DEVELOPER");

    private String roleName;

    EUserRole(String name) {
        this.roleName = name;
    }

}