package com.jamsirat.atmapi.base;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public class PaginationUtil {


    public static <T,R> PaginatedResponse<R> toPaginationResponse(Page<T> page, Function<T,R> converter) {
        List<R> data = page.stream()
                .map(converter)
                .toList();

        return new PaginatedResponse<>(
                data,
                page.getNumber(),
                page.getSize(),
                page.getNumberOfElements(),
                page.getTotalPages()
                );
    }


}
