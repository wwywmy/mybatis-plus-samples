package com.baomidou.mybatisplus.samples.quickstart;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.samples.quickstart.entity.Course;
import com.baomidou.mybatisplus.samples.quickstart.mapper.CourseMapper;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CourseTest {

	@Resource
	private CourseMapper courseMapper;

	private final static List<String> courseList;

	static {
		courseList = Arrays.asList("C语言程序设计", "计算机组成原理", "数据结构", "操作系统", "微机原理及汇编语言", "计算机网络", "计算机系统结构", "软件工程",
				"面向对象程序设计", "面向对象程序设计", "计算机组成原理", "操作系统", "数据结构", "计算", "机网络", "软件工程", "编译原理", "分布式系统", "软件项目管理",
				"Oracle数据库系统", "管理学原理", "电子商务", "物流管理", "计算机网络", "供应链管理", "电子商务平台及核心技术", "国际商务管理", "电子商务案例分析",
				"商务网站建设");
	}

	@Test
	public void insertTest() {
		
		long workerId = 1L; // 终端ID
		long datacenterId = 1L;// 数据中心ID
		log.info("workerId={}", workerId);
		log.info("datacenterId={}", datacenterId);
		Snowflake snowflake  = IdUtil.createSnowflake(workerId, datacenterId);
		
		for(int i=0;i<3;i++) {
			Course course = new Course();
			course.setId(snowflake.nextId());
			course.setName(courseList.get(RandomUtil.randomInt(courseList.size())));

			int count = courseMapper.insert(course);
			log.info("count={}", count);
		}
	}

}
