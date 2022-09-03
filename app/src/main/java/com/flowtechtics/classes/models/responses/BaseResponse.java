package com.flowtechtics.classes.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BaseResponse {

    private String status_message;
    private boolean success;
    private int status_code;


}
