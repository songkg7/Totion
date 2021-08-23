package com.lambda.totion.dto.notion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
public class PageResult {

    private String object;
    private String type;

    @JsonInclude(Include.NON_NULL)
    private Paragraph paragraph;

}
