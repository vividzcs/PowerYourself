package com.nwuer.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nwuer.core.common.Const;
import com.nwuer.core.common.ResponseCode;
import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.dao.TaskMapper;
import com.nwuer.core.entity.Task;
import com.nwuer.core.service.IJobService;
import com.nwuer.core.vo.JobFormVo;
import com.nwuer.core.vo.JobListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PageInfo<JobListVo> listJob(Integer pageNum,String id,String uid,Integer is_finished) {
        PageHelper.startPage(pageNum, Const.PAGE_SIZE);
        List<JobListVo> list = taskMapper.selectAll(id,uid,is_finished);
        PageInfo<JobListVo> page = new PageInfo<JobListVo>(list);
        return page;
    }

    @Transactional
    public void addJob(Task task) {
        taskMapper.insert(task);
    }

    public void delete(String id) {
        taskMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public ServerResponse doneJob(String id) {
        int affectCount = taskMapper.doneJob(id);
        if(affectCount >0 ){
            //删除定时任务

            return ServerResponse.createBySuccess("操作成功");
        }else{
            return ServerResponse.createByErrorMessage(ResponseCode.UNKNOWN_ERROR.getCode(),ResponseCode.UNKNOWN_ERROR.getDesc());
        }
    }

    public PageInfo<JobListVo> listJobByCategory(Integer pageNum,String id, String uid,Integer is_finished) {
        PageHelper.startPage(pageNum, Const.PAGE_SIZE);
        List<JobListVo> list = taskMapper.selectAll(id,uid,is_finished);
        PageInfo<JobListVo> page = new PageInfo<JobListVo>(list);
        return page;
    }

    /**
     * 改变任务状态
     */
    @Transactional
    public void checkJobStatus(String uid){
        taskMapper.checkJobStatus(uid);
    }

    public Integer CountJobByStatus(Integer status,String uid){
        return taskMapper.countJobByStatus(status,uid);
    }

    public ServerResponse selectJobForUpdate(String uid, String id) {
        JobFormVo job = taskMapper.selectJobForUpdate(uid,id);
        if(job == null){
            return ServerResponse.createByErrorMessage("无此任务");
        }else{
            return ServerResponse.createBySuccess(job);
        }
    }

    @Transactional
    public void updateByPrimaryKeySelective(Task task){
        taskMapper.updateByPrimaryKeySelective(task);
    }
}
