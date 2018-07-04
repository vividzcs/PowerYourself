package com.nwuer.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author vividzc
 * @date 2018/6/30 0:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    private String title;
    private Date beganTime;
    private Date endTime;
    private Date remindTime;
    private String notation;
}
