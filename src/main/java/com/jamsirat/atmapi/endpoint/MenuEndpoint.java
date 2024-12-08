package com.jamsirat.atmapi.endpoint;


import com.jamsirat.atmapi.dto.response.HttpResponse;
import com.jamsirat.atmapi.dto.response.ResponseMenuMapper;
import com.jamsirat.atmapi.service.impl.MenuServiceImpl;
import com.jamsirat.atmapi.statval.constant.IApplicationConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;
import java.util.List;

import static com.jamsirat.atmapi.statval.constant.IApplicationConstant.Path.Menu.*;
import static com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.DeveloperSuccessMessage;
import static com.jamsirat.atmapi.statval.constant.IApplicationConstant.StaticDefaultMessage.SuccessMessage;

@RequestMapping(IApplicationConstant.ContextPath.MENU)
@RestController
@RequiredArgsConstructor
public class MenuEndpoint {

    private final MenuServiceImpl menuService;

    @GetMapping(FETCH_MENU)
    private HttpResponse<Object> fetchMenu(Principal principal) {
        List<ResponseMenuMapper> menuResponse = menuService.fetchMenuAccordingToRole(principal);
        if (menuResponse == null || menuResponse.isEmpty()) {
          return HttpResponse.noContent();
        }
          return HttpResponse.build(DeveloperSuccessMessage.DATA_FETCH_SUCCESSFULLY,SuccessMessage.DATA_FETCH_SUCCESSFULLY, HttpStatus.OK,menuResponse);
    }

}
