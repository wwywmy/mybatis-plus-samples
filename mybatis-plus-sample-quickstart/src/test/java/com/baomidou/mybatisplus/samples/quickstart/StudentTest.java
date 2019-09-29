package com.baomidou.mybatisplus.samples.quickstart;

import java.util.Locale;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.samples.quickstart.entity.Student;
import com.baomidou.mybatisplus.samples.quickstart.mapper.StudentMapper;
import com.github.javafaker.Faker;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StudentTest {

	@Resource
	private StudentMapper studentMapper;

	@Test
	public void insertTest() {
		Student student = new Student();
		student.setName(new Faker(Locale.CHINA).name().fullName());
		student.setAge(RandomUtil.randomInt(18, 30 + 1));

		int count = studentMapper.insert(student);
		log.info("count={}", count);
	}

	@Test
	public void deleteAllTest() {
		studentMapper.deleteAll();
	}

}
