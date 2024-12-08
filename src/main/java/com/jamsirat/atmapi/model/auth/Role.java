package com.jamsirat.atmapi.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jamsirat.atmapi.model.Base.AAuditableBase;
import com.jamsirat.atmapi.model.Base.BaseMasterData;
import com.jamsirat.atmapi.model.config.Menu;
import com.jamsirat.atmapi.model.config.Permission;
import com.jamsirat.atmapi.statval.enumeration.EUserRole;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
@Entity
@Builder
@Setter
@Getter
public class Role extends AAuditableBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 783521278187749739L;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private EUserRole userRole;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id")
    )
    private Set<Permission> permissions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_menu",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id")
    )
    private Set<Menu> menus;

    public Role(String roleName) {
        super();
    }

    public String getRoleName() {
        return userRole.getRoleName();
    }

}