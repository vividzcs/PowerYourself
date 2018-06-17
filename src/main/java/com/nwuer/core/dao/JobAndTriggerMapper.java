package com.nwuer.core.dao;

import com.nwuer.core.entity.JobAndTrigger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 查询任务相关信息
 * @author vividzc
 * @date 2018/6/14 22:56
 */
@Mapper
public interface JobAndTriggerMapper {
    public List<JobAndTrigger> getJobAndTriggerDetails();
}
