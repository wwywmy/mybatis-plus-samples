package com.baomidou.mybatisplus.samples.quickstart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.samples.quickstart.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StudentTransactionalTest {

	@Autowired
	private StudentService studentService;

	@Test
	public void insertTest() {
		int count = studentService.insert();
		
		log.info("count={}",count);
	}

	

}
