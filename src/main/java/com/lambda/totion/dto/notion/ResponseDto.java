package com.lambda.totion.dto.notion;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ResponseDto {

    private String object;  // list
    private List<DBResult> results = new ArrayList<>();

}
