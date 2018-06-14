package com.nwuer.core.vo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultVo<T> {

    private int code;

    private String message;

    private T data;
}
