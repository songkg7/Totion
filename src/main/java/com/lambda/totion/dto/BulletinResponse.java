package com.lambda.totion.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class BulletinResponse {

    private String object;
    private List<Result> results = new ArrayList<>();

}
