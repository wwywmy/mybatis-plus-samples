package com.baomidou.mybatisplus.samples.quickstart.service.impl;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.samples.quickstart.entity.Student;
import com.baomidou.mybatisplus.samples.quickstart.entity.Teacher;
import com.baomidou.mybatisplus.samples.quickstart.mapper.StudentMapper;
import com.baomidou.mybatisplus.samples.quickstart.mapper.TeacherMapper;
import com.baomidou.mybatisplus.samples.quickstart.service.StudentService;
import com.github.javafaker.Faker;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService{

	@Resource
	private StudentMapper studentMapper;
	
	@Resource
	private TeacherMapper teacherMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class) 
	public int insert() {
		Student student = new Student();
		student.setName(new Faker(Locale.CHINA).name().fullName());
		student.setAge(RandomUtil.randomInt(18, 30 + 1));

		int count = studentMapper.insert(student);
		
		log.info("student id={}",student.getId());
		
		
		
		int x = RandomUtil.randomBoolean()?1:0;
		
		log.info("x={}",x);
		
		x = 100/x;
		
		log.info("x={}",x);
		
		Teacher teacher = new Teacher();
		teacher.setNo(RandomUtil.randomNumbers(4));
		teacher.setName(new Faker(Locale.CHINA).name().fullName());

		teacherMapper.insert(teacher);
		
		log.info("teacher id={}",teacher.getId());
		
		return count;
	}

}
