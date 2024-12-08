package com.jamsirat.atmapi.service.impl;


import com.jamsirat.atmapi.dto.response.ResponseMenuMapper;
import com.jamsirat.atmapi.mapper.MenuMapper;
import com.jamsirat.atmapi.repository.IMenuRepository;
import com.jamsirat.atmapi.service.IAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl {

    private final IMenuRepository menuRepository;

    private final IAuthorizationService iAuthorizationService;

    private final MenuMapper menuMapper;
    public List<ResponseMenuMapper> fetchMenuAccordingToRole(Principal principal) {
        //Get the roles of the user;
       List<String> activeRoles =  iAuthorizationService.getRolesByLoggedInUser(principal);
        //fetch menu based on roles;
       return menuMapper.entitiesIntoDTOs(menuRepository.findAllByRoles_NamesIn(activeRoles));
    }


}
