package com.lambda.totion.dto.notion;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RequestDto {

    private List<Sort> sorts = new ArrayList<>();
    private Filter filter;

    @Data
    @AllArgsConstructor
    public static class Sort {

        private String property;
        private String direction;

    }

    @Data
    @AllArgsConstructor
    public static class Filter {

        private String property;
        private Select select;

    }

    @Data
    @AllArgsConstructor
    public static class Select {

        private String equals;

    }

}
