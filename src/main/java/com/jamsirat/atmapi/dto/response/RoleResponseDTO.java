package com.jamsirat.atmapi.dto.response;


import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RoleResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1229350778594552666L;

    private long roleId;
    private String role;

}
