package com.gg.photogram.web.dto.ex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> {

    private int code;
    private String status;
    private T data;

}
