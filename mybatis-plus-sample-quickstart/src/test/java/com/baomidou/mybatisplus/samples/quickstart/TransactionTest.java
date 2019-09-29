package com.baomidou.mybatisplus.samples.quickstart;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.samples.quickstart.entity.Student;
import com.baomidou.mybatisplus.samples.quickstart.mapper.StudentMapper;
import com.github.javafaker.Faker;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TransactionTest {
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
	public void selectListTest() {
		List<Student> studentList = studentMapper.selectList(null);
		studentList.forEach(s->{log.info(s.toString());});
	}
	
	@Test
	public void updateTest() {
		Student student6 = studentMapper.selectById(6L);
		Student student7 = studentMapper.selectById(7L);
		
		log.info(student6.toString());
		log.info(student7.toString());
		
		student6.setAge(student6.getAge()+1);
		student7.setAge(student7.getAge()-1);
		
		studentMapper.updateById(student6);
		studentMapper.updateById(student7);
		
		student6 = studentMapper.selectById(6L);
		student7 = studentMapper.selectById(7L);
		
		log.info(student6.toString());
		log.info(student7.toString());
		
	}
	
	@Test
	public void update2Test() {
		Student student6 = studentMapper.selectById(6L);
		Student student7 = studentMapper.selectById(7L);
		
		log.info(student6.toString());
		log.info(student7.toString());
		
		student6.setAge(student6.getAge()+1);
		student7.setAge(student7.getAge()-1);
		
		studentMapper.updateById(student6);
		int i=0;
		log.info(Integer.toString(1/i)); //出现异常，更新7失败
		studentMapper.updateById(student7);
		
		student6 = studentMapper.selectById(6L);
		student7 = studentMapper.selectById(7L);
		
		log.info(student6.toString());
		log.info(student7.toString());
		
	}
	
	@Test
	@Transactional
	public void update3Test() {
		Student student6 = studentMapper.selectById(6L);
		Student student7 = studentMapper.selectById(7L);
		
		log.info(student6.toString());
		log.info(student7.toString());
		
		student6.setAge(student6.getAge()+1);
		student7.setAge(student7.getAge()-1);
		
		studentMapper.updateById(student6);
		int i=0;
		log.info(Integer.toString(1/i)); //出现异常，更新7失败
		studentMapper.updateById(student7);
		
		student6 = studentMapper.selectById(6L);
		student7 = studentMapper.selectById(7L);
		
		log.info(student6.toString());
		log.info(student7.toString());
		
	}
}
