package com.baomidou.mybatisplus.samples.quickstart;

import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

	@Test
	public void selectList() {
		teacherMapper.selectList(new QueryWrapper<Teacher>()).forEach(t -> {
			// log.info(ToStringBuilder.reflectionToString(t,ToStringStyle.JSON_STYLE));
			log.info(ToStringBuilder.reflectionToString(t, ToStringStyle.SHORT_PREFIX_STYLE));
		});
	}

	@Test
	public void selectPage() {
		IPage<Teacher> page = new Page<Teacher>(1, 3);
		page.orders().add(OrderItem.asc("no"));
		//page.orders().add(OrderItem.asc("id"));
		QueryWrapper<Teacher> queryWrapper = new QueryWrapper<Teacher>();
		queryWrapper.lambda().orderByAsc(Teacher::getId);
		IPage<Teacher> teacherPage = teacherMapper.selectPage(page, queryWrapper);

		teacherPage.getRecords().forEach(t -> {
			// log.info(ToStringBuilder.reflectionToString(t,ToStringStyle.JSON_STYLE));
			log.info(ToStringBuilder.reflectionToString(t, ToStringStyle.SHORT_PREFIX_STYLE));
		});

		// 	SELECT id, no, name FROM teacher ORDER BY no LIMIT 0,3
		//	SELECT id, no, name FROM teacher ORDER BY id LIMIT 0,3
		//	SELECT id, no, name FROM teacher ORDER BY id ASC, no LIMIT 0,3
	}

}
