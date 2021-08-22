package com.lambda.totion.dto;

import java.util.List;
import lombok.Data;

@Data
public class ResponseDto {

    private String object;  // list
    private List<Result> results;

}
