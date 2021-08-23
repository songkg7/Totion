package com.lambda.totion.dto.notion;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class BulletinResponse {

    private String object;
    private List<DBResult> DBResults = new ArrayList<>();

}
