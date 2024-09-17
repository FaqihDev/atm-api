package com.jamsirat.atmapi.mapper;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.RoleResponseDTO;
import com.jamsirat.atmapi.model.auth.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class RoleUserMapper extends ADATAMapper<Role, RoleResponseDTO> {


    @Override
    public RoleResponseDTO convert(Role role) {
        return RoleResponseDTO.builder()
                .roleId(role.getId())
                .role(role.getRoleName())
                .build();

    }
}
