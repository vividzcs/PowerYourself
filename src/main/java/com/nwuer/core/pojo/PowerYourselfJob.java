package com.nwuer.core.pojo;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author vividzc
 * @date 2018/6/14 23:03
 */
@Slf4j
public class PowerYourselfJob extends BaseJob {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("PowerYouself执行:"+new Date().toString());
    }
}
