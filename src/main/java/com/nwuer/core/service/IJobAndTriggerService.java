package com.nwuer.core.service;

import com.github.pagehelper.PageInfo;
import com.nwuer.core.entity.JobAndTrigger;

/**
 * @author vividzc
 * @date 2018/6/14 23:16
 */
public interface IJobAndTriggerService {
    public PageInfo<JobAndTrigger> queryJob(int pageNum, int pageSize);
}
