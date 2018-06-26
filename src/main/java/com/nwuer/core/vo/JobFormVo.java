package com.nwuer.core.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;

/**
 * @author vividzc
 * @date 2018/6/24 10:03
 */
@Data
@NoArgsConstructor
public class JobFormVo {
    @NotEmpty(message = "标题不能为空")
    private String title;
    @NotEmpty
    @Length(min = 32,max = 32,message = "系统错误")
    private String taskCategoryId;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private String remindTime;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private String endTime;
    @NotEmpty(message = "内容不能为空")
    private String notation;

    //
    private String id;
    private String categoryName;



}
