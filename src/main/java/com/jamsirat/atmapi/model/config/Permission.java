package com.jamsirat.atmapi.model.config;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jamsirat.atmapi.model.Base.AAuditableBase;
import com.jamsirat.atmapi.model.auth.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permission")
@Entity
@Builder
@Setter
@Getter
public class Permission extends AAuditableBase implements Serializable {

    @Column
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<Menu> menus;
}
