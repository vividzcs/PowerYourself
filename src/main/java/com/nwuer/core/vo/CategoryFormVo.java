package com.nwuer.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author vividzc
 * @date 2018/6/23 16:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryFormVo {
    @NotEmpty(message = "分类名不能为空")
    private String categoryName;
    @NotEmpty(message = "父级错误")
    private String parentId;

    private String userId;

    private String id;

}
