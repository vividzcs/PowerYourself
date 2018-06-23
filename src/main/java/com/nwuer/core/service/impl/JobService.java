package com.nwuer.core.service.impl;

import com.nwuer.core.dao.TaskMapper;
import com.nwuer.core.entity.Task;
import com.nwuer.core.service.IJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vividzc
 * @date 2018/6/23 23:26
 */
@Slf4j
@Service
public class JobService implements IJobService {
    @Autowired
    private TaskMapper taskMapper;

    /**
     *
     * @param id  User Id
     * @return
     */
    public List<Task> listJob(String id) {
        return taskMapper.selectAll(id);
    }

}
