package com.nwuer.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author vividzc
 * @date 2018/6/24 13:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobListVo {
    private String id;
    private String title;
    private Date beganTime;
    private Date remindTime;
    private Date endTime;
    private String categoryName;
    private String notation;
    private Integer progress;
}
