package com.baomidou.mybatisplus.samples.quickstart;

import java.util.Locale;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.samples.quickstart.entity.Address;
import com.baomidou.mybatisplus.samples.quickstart.entity.User;
import com.baomidou.mybatisplus.samples.quickstart.mapper.UserMapper;
import com.github.javafaker.Faker;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserAddressTest {

	@Resource
	private UserMapper userMapper;

	@Test
	public void insertTest() {
		User user = new User();
		user.setId(RandomUtil.randomLong(1L, 100L + 1L));
		user.setId(RandomUtil.randomLong(1L, 5L + 1L));
		user.setName(new Faker(Locale.CHINA).name().fullName());
		user.setAge(RandomUtil.randomInt(0, 100 + 1));
		user.setEmail(RandomUtil.randomNumbers(8) + "@qq.com");

		Address address = new Address();
		address.setProvince(new Faker(Locale.CHINA).address().state());
		address.setCity(new Faker(Locale.CHINA).address().city());

		user.setAddress(address);

		int count = userMapper.insert(user);
		log.info("count={}", count);
	}

	@Test
	public void selectByIdTest() {
		User user = userMapper.selectById(4L);
		log.info(user.toString());
	}

	@Test
	public void updateByIdTest() {
		User user = new User();
		user.setId(4L);
		Address address = new Address();
		address.setProvince(new Faker(Locale.CHINA).address().state());
		user.setAddress(address);
		int count = userMapper.updateById(user);
		log.info("count={}", count);

		// UPDATE user SET address = '{"city":"","province":"重庆市"}' WHERE user.tenant_id
		// = tenant_id IN (1, 2) AND id = 4
	}

	@Test
	public void updateById2Test() {
		User user = userMapper.selectById(4L);
		Address address = user.getAddress();
		address.setProvince(new Faker(Locale.CHINA).address().state());
		address.setCity(new Faker(Locale.CHINA).address().city());
		int count = userMapper.updateById(user);
		log.info("count={}", count);

		// UPDATE user SET address = '{"city":"厦海市","province":"广西省"}', tenant_id = 1,
		// name = '姚智渊', email = '92893775@qq.com', age = 21 WHERE user.tenant_id =
		// tenant_id IN (1, 2) AND id = 4
	}

}
