package com.jamsirat.atmapi.dto.request;


import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FetchMenuRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;

}
