package com.nwuer.core.controller.user;

import com.github.pagehelper.PageInfo;
import com.nwuer.core.common.Const;
import com.nwuer.core.common.ServerResponse;
import com.nwuer.core.common.util.CronExpressionUtil;
import com.nwuer.core.common.util.DateParseForCronExpressionUtil;
import com.nwuer.core.common.util.UuidUtil;
import com.nwuer.core.dto.UserDto;
import com.nwuer.core.entity.Task;
import com.nwuer.core.pojo.PowerYourselfJob;
import com.nwuer.core.service.impl.CategoryService;
import com.nwuer.core.service.impl.JobAndTriggerServiceImpl;
import com.nwuer.core.service.impl.JobService;
import com.nwuer.core.vo.JobFormVo;
import com.nwuer.core.vo.JobListVo;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vividzc
 * @date 2018/6/23 23:23
 */
@Controller
@RequestMapping("/user/job")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JobAndTriggerServiceImpl jobAndTriggerService;

    @RequestMapping(value = {"/","/{pageNum}"}, method = RequestMethod.GET)
    public ModelAndView listJob(@PathVariable(value = "pageNum",required = false) Integer pageNum,HttpSession session){
        if(pageNum == null){
            pageNum = 1;
        }
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/job");

        //change status
        jobService.checkJobStatus(user.getId());

        //listJob(Integer pageNum,String id,String uid,Integer is_finished)
        PageInfo<JobListVo> list = jobService.listJob(pageNum,null,user.getId(),Const.JOB_NORMAL);

        mv.addObject("allJob",list);
        return mv;
    }

    @RequestMapping(value = {"/finished","/finished/{pageNum}"}, method = RequestMethod.GET)
    public ModelAndView finishedJob(@PathVariable(value = "pageNum",required = false) Integer pageNum,HttpSession session){
        if(pageNum == null){
            pageNum = 1;
        }
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/job_finished");

        PageInfo<JobListVo> list = jobService.listJob(pageNum,null,user.getId(),Const.JOB_FINISHED);

        mv.addObject("allJob",list);
        return mv;
    }

    @RequestMapping(value = {"/over_date","/over_date/{pageNum}"}, method = RequestMethod.GET)
    public ModelAndView overDateJob(@PathVariable(value = "pageNum",required = false) Integer pageNum,HttpSession session){
        if(pageNum == null){
            pageNum = 1;
        }
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/job_over_date");

        PageInfo<JobListVo> list = jobService.listJob(pageNum,null,user.getId(),Const.JOB_OVER_DATE);

        mv.addObject("allJob",list);
        return mv;
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id,HttpSession session) {
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        if (id == null || id.trim().length() != 32) {
            mv.setViewName("/error");
            mv.addObject("msg", "参数错误");
            return mv;
        }
        //查出来
        ServerResponse res = jobService.selectJobForUpdate(user.getId(),id);
        if(res.isSuccess()){
            List<Object[]> listOrdered = categoryService.getCategoryOrdered(user.getId(),Const.CATEGORY_ROOT_ID);
            for(int i=0; i<listOrdered.size();i++) {
                StringBuilder str = new StringBuilder();
                for(int j=0;j<(Integer)listOrdered.get(i)[1];j++) {
                    str.append("&nbsp;&nbsp;");
                }
                listOrdered.get(i)[1] = str;
            }

            mv.setViewName("/user/job_edit");
            mv.addObject("job",res.getData());
            mv.addObject("allCategoryOrdered",listOrdered);
        }

        return mv;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ModelAndView updateJob(@Valid JobFormVo jobFormVo, HttpSession session,
                               Errors errors) throws ParseException, SchedulerException {
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();

        Date remindTime = DateParseForCronExpressionUtil.getDate(jobFormVo.getRemindTime());
        Date endTime = DateParseForCronExpressionUtil.getDate(jobFormVo.getEndTime());
        Date now = new Date();

        if(errors.hasErrors() || now.after(remindTime) || now.after(endTime) || remindTime.after(endTime)){
            mv.setViewName("/user/job_edit");


            ServerResponse res = jobService.selectJobForUpdate(user.getId(),jobFormVo.getId());
            List<Object[]> listOrdered = categoryService.getCategoryOrdered(user.getId(),Const.CATEGORY_ROOT_ID);
            for(int i=0; i<listOrdered.size();i++) {
                StringBuilder str = new StringBuilder();
                for(int j=0;j<(Integer)listOrdered.get(i)[1];j++) {
                    str.append("&nbsp;&nbsp;");
                }
                listOrdered.get(i)[1] = str;
            }

            mv.addObject("allCategoryOrdered",listOrdered);
            mv.addObject("job",res.getData());
            mv.addObject("msg","信息填写有误(可能日期为历史,或某项没填)");
        }else {
            mv.setViewName("/user/job");
            //添加
            Task task = new Task();
            task.setId(jobFormVo.getId());
            task.setRemindTime(remindTime);
            task.setEndTime(endTime);
            task.setTitle(jobFormVo.getTitle());
            task.setNotation(jobFormVo.getNotation());
            task.setTaskCategoryId(jobFormVo.getTaskCategoryId());
            jobService.updateByPrimaryKeySelective(task);

            PowerYourselfJob job = new PowerYourselfJob();
            job.setJobName(task.getId());
            job.setJobGroup(user.getId());
            job.setDescription(jobFormVo.getNotation());
            Integer[] date = DateParseForCronExpressionUtil.parse(jobFormVo.getRemindTime());
            job.setCronExpression(
                    CronExpressionUtil.getCronExpressionSpecialDay(date[0],date[1],date[2],date[3],date[4],date[5],date[6])
            );
            jobAndTriggerService.rescheduleJob(job.getJobName(),job.getJobGroup(),job.getCronExpression(),job.getDescription());

            return listJob(1,session);

        }

        return mv;
    }

    @RequestMapping(value = "/delete/{id}",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ServerResponse delete(@PathVariable String id,HttpSession session) throws SchedulerException {
        UserDto userDto = (UserDto) session.getAttribute(Const.CURRENT_USER);
        if(id != null && id.trim().length() ==  32){
            jobService.delete(id);
            jobAndTriggerService.deleteJob(id,userDto.getId());
            return ServerResponse.createBySuccess("操作成功");
        }else{
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    @RequestMapping(value = "/done/{id}",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ServerResponse doneJob(@PathVariable String id,HttpSession session ) throws SchedulerException {
        UserDto userDto = (UserDto) session.getAttribute(Const.CURRENT_USER);
        if(id == null || id.length() != 32) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        ServerResponse res = jobService.doneJob(id);
        if(res.isSuccess()){
            jobAndTriggerService.deleteJob(id,userDto.getId());
        }
        return res;
    }

    @RequestMapping(value = "/to_add",method = RequestMethod.GET)
    public ModelAndView toAdd(HttpSession session) {
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/job_post");

        List<Object[]> listOrdered = categoryService.getCategoryOrdered(user.getId(),Const.CATEGORY_ROOT_ID);
        for(int i=0; i<listOrdered.size();i++) {
            StringBuilder str = new StringBuilder();
            for(int j=0;j<(Integer)listOrdered.get(i)[1];j++) {
                str.append("&nbsp;&nbsp;");
            }
            listOrdered.get(i)[1] = str;
        }

        mv.addObject("allCategoryOrdered",listOrdered);
        return mv;
    }



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addJob(@Valid JobFormVo jobFormVo, HttpSession session,
                               Errors errors) throws ParseException, SchedulerException {
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();


        if(errors.hasErrors()){
            mv.setViewName("/user/job_post");

            List<Object[]> listOrdered = categoryService.getCategoryOrdered(user.getId(),Const.CATEGORY_ROOT_ID);
            for(int i=0; i<listOrdered.size();i++) {
                StringBuilder str = new StringBuilder();
                for(int j=0;j<(Integer)listOrdered.get(i)[1];j++) {
                    str.append("&nbsp;&nbsp;");
                }
                listOrdered.get(i)[1] = str;
            }

            mv.addObject("allCategoryOrdered",listOrdered);
            mv.addObject("msg","信息填写有误(可能日期为历史,或某项没填)");
        }else{
            mv.setViewName("/user/job");
            //添加
            Task task = new Task();
            task.setId(UuidUtil.get32UUID());
            task.setRemindTime(DateParseForCronExpressionUtil.getDate(jobFormVo.getRemindTime()));
            task.setBeganTime(new Date());
            task.setEndTime(DateParseForCronExpressionUtil.getDate(jobFormVo.getEndTime()));
            task.setTitle(jobFormVo.getTitle());
            task.setNotation(jobFormVo.getNotation());
            task.setUserId(user.getId());
            task.setTaskCategoryId(jobFormVo.getTaskCategoryId());
            jobService.addJob(task);

            PowerYourselfJob job = new PowerYourselfJob();
            job.setJobName(task.getId());
            job.setJobGroup(user.getId());
            job.setDescription(jobFormVo.getNotation());
            Integer[] date = DateParseForCronExpressionUtil.parse(jobFormVo.getRemindTime());
            job.setCronExpression(
                    CronExpressionUtil.getCronExpressionSpecialDay(date[0],date[1],date[2],date[3],date[4],date[5],date[6])
                    );
            jobAndTriggerService.addJob(job);

            return listJob(1,session);

        }

        return mv;
    }

    /**
     * 根据分类来获取任务
     * @param id
     * @return
     */
    @RequestMapping(value = {"/find/{id}/{pageNum}","/find/{id}"},method = RequestMethod.GET)
    public ModelAndView showListByCategory(@PathVariable(required = false) String id,@PathVariable(required = false) Integer pageNum,HttpSession session){
        //TODO 验证后如果错误转错误页面
        if(id == null || id.length() != 32){
            id = null;
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/index");
        if(pageNum == null){
            pageNum = 1;
        }
        UserDto userDto = (UserDto) session.getAttribute(Const.CURRENT_USER);
        PageInfo<JobListVo> list = jobService.listJobByCategory(pageNum,id,userDto.getId(),Const.JOB_NORMAL);
        mv.addObject("allJob",list);
        mv.addObject("cate_id",id);
        return mv;
    }

    @RequestMapping(value = "/statistic",method = RequestMethod.GET)
    public ModelAndView showStatistic(HttpSession session){
        UserDto userDto = (UserDto) session.getAttribute(Const.CURRENT_USER);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/statistic");
        //查询数据
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("normal",jobService.CountJobByStatus(Const.JOB_NORMAL,userDto.getId()));
        map.put("finished",jobService.CountJobByStatus(Const.JOB_FINISHED,userDto.getId()));
        map.put("overdate",jobService.CountJobByStatus(Const.JOB_OVER_DATE,userDto.getId()));
        mv.addAllObjects(map);
        return mv;
    }

}
