package com.nwuer.core;

import com.nwuer.core.common.util.DateParseForCronExpressionUtil;
import com.nwuer.core.pojo.PowerYourselfJob;
import com.nwuer.core.service.impl.JobAndTriggerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PowerYourselfApplicationTests {
	@Autowired
	private JobAndTriggerServiceImpl jobAndTriggerService;

	/**
	 * 关于quartz的测试全部通过
	 * 想增加查询的方法可以在JobAndTriggerMapper里面写方法,然后在JobAndTrigger.xml里面写sql语句,可以仿照之前写了
	 * @throws SchedulerException
	 */
	@Test
	public void contextLoads() throws SchedulerException {
		PowerYourselfJob job = new PowerYourselfJob();
		job.setJobName("测试");
		job.setCronExpression("0 26 0  * * ?");
		jobAndTriggerService.addJob(job); //pass

//		System.out.println(jobAndTriggerService.queryJob(3,3));  //
//		jobAndTriggerService.pauseJob("测试","default");
//		jobAndTriggerService.resumeJob("测试","default");
//		jobAndTriggerService.rescheduleJob("测试","default","0 27 0  * * ?");
//		jobAndTriggerService.deleteJob("测试","default");
	}

	@Test
	public void test() throws ParseException {
		System.out.println(DateParseForCronExpressionUtil.parse("2019-04-22 22:12:33"));

	}

}
