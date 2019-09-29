package com.baomidou.mybatisplus.samples.quickstart;

import java.util.Locale;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.samples.quickstart.entity.Teacher;
import com.baomidou.mybatisplus.samples.quickstart.mapper.TeacherMapper;
import com.github.javafaker.Faker;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TeacherTest {

	@Resource
	private TeacherMapper teacherMapper;

	@Test
	public void insertTest() {
		Teacher teacher = new Teacher();
		teacher.setNo(RandomUtil.randomNumbers(4));
		teacher.setName(new Faker(Locale.CHINA).name().fullName());

		int count = teacherMapper.insert(teacher);
		log.info("count={}", count);
	}

}
