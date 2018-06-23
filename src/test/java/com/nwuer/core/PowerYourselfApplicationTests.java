package com.nwuer.core;

import com.nwuer.core.common.util.DateParseForCronExpressionUtil;
import com.nwuer.core.common.util.MD5;
import com.nwuer.core.common.util.UuidUtil;
import com.nwuer.core.dao.UserMapper;
import com.nwuer.core.entity.User;
import com.nwuer.core.pojo.PowerYourselfJob;
import com.nwuer.core.pojo.Role;
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
	@Autowired
	private UserMapper userMapper;
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

	@Test
	public void testMd5(){
//		System.out.println(MD5.md5("1234"));
		User user = new User();
		user.setId(UuidUtil.get32UUID());
		user.setUsername("dzc");
		user.setEmail("1747789689@qq.com");
		user.setPassword(MD5.md5("123"));
		user.setRole(Role.ORDINARY.getLevel());
		user.setActivated(Boolean.FALSE);
		userMapper.insert(user);
	}

	public void addUser() {

	}

}
