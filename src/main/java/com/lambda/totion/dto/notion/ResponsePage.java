package com.lambda.totion.dto.notion;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ResponsePage {

    private String object;
    private List<PageResult> results = new ArrayList<>();

}
