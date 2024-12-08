package com.jamsirat.atmapi.dto.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMenuMapper implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long parentMenuId;
    private String menuName;
    private String url;
    private boolean isPublic;
    private List<ResponseMenuMapper> subMenu;
}
