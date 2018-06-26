package com.nwuer.core.dao;

import com.nwuer.core.entity.Task;
import com.nwuer.core.vo.JobFormVo;
import com.nwuer.core.vo.JobListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper {
    int deleteByPrimaryKey(String id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKeyWithBLOBs(Task record);

    int updateByPrimaryKey(Task record);

    List<JobListVo> selectAll( @Param("id") String id,@Param("uid") String uid, @Param("is_finished") Integer is_finished);

    int doneJob(String id);

    void checkJobStatus(@Param("uid") String uid);

    Integer countJobByStatus(@Param("status") Integer status, @Param("uid") String uid);

    JobFormVo selectJobForUpdate(@Param("uid") String uid, @Param("id") String id);

    void deleteByUserId(String id);
}