package com.jamsirat.atmapi.base;

import lombok.*;


import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PaginatedResponse <T> implements Serializable {
    private static final long serialVersionUUID = 1L;
    private List<T> data;
    private int page;
    private int size;
    private long totalElement;
    private int totalPages;
}
