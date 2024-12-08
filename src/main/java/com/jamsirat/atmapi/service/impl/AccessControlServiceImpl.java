package com.jamsirat.atmapi.service.impl;


import com.jamsirat.atmapi.model.config.Menu;
import com.jamsirat.atmapi.model.config.Permission;
import com.jamsirat.atmapi.repository.IMenuRepository;
import com.jamsirat.atmapi.repository.IRoleRepository;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccessControlServiceImpl {

    private final IRoleRepository roleRepository;
    private final IMenuRepository menuRepository;

    public boolean hasAccess (String username, String endpoint) {

        //Get the permission for every user
        Set<String> permissions = roleRepository.findPermissionByUsername(username);
        //Get menu
        Menu menuByUrl = menuRepository.findByUrl(endpoint);

        if (menuByUrl == null) {
            return false;
        }

        return permissions.stream().anyMatch(p ->
                menuByUrl.getPermissions().stream().map(Permission::getName).anyMatch(permissions::equals));
    }

}
