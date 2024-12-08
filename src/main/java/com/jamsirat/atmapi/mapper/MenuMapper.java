package com.jamsirat.atmapi.mapper;

import com.jamsirat.atmapi.BaseMapper.ADATAMapper;
import com.jamsirat.atmapi.dto.response.ResponseMenuMapper;
import com.jamsirat.atmapi.model.config.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class MenuMapper extends ADATAMapper<Menu,ResponseMenuMapper> {


    @Override
    public ResponseMenuMapper convert(Menu menu) {


        ResponseMenuMapper response = ResponseMenuMapper.builder()
                .parentMenuId(Objects.isNull(menu.getParentId()) ? null : menu.getParentId())
                .menuName(menu.getName())
                .isPublic(menu.isPublicMenu())
                .url(menu.getUrl())
                .build();


            if (Objects.nonNull(menu.getSubMenus()) && !menu.getSubMenus().isEmpty()) {
                response.setSubMenu(menu.getSubMenus().stream()
                        .map(this::convert)
                        .toList());
            }
                return response;

    }
}
