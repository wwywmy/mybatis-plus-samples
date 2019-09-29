package com.baomidou.mybatisplus.samples.quickstart;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.samples.quickstart.entity.User;
import com.baomidou.mybatisplus.samples.quickstart.enums.GenderEnum;
import com.baomidou.mybatisplus.samples.quickstart.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserGenderTest {

	@Resource
	private UserMapper userMapper;

	@Test
	public void updateById() {

		for (long i = 1L; i <= 100L; i++) {
			User user = new User();
			user.setId(i);
			user.setGender(GenderEnum.getEnum(RandomUtil.randomString("MFU", 1)));

			int count = userMapper.updateById(user);
			log.info("count={}", count);
		}

	}

	@Test
	public void selectListTest(){
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		// wrapper.lambda().and(e->e.ge(User::getId, 30).and(j->j.lt(User::getId, 40)));
		wrapper.lambda().ge(User::getId, 30).lt(User::getId, 40);
		List<User> userList = userMapper.selectList(wrapper);
		// userList.forEach(e -> log.info(e.toString()));
		
		userList.forEach(e -> {
			try {
				log.info(new ObjectMapper().writeValueAsString(e));
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		
			
	}

}
