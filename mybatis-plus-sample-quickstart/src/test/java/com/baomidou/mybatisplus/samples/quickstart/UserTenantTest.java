package com.baomidou.mybatisplus.samples.quickstart;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.samples.quickstart.entity.User;
import com.baomidou.mybatisplus.samples.quickstart.mapper.UserMapper;
import com.github.javafaker.Faker;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTenantTest {

	@Resource
	private UserMapper userMapper;

	@Test
	public void insertTest() {
		User user = new User();
		user.setId(RandomUtil.randomLong(1L, 100L + 1L));
		user.setName(new Faker(Locale.CHINA).name().fullName());
		user.setAge(RandomUtil.randomInt(0, 100 + 1));
		user.setEmail(RandomUtil.randomNumbers(8) + "@qq.com");

		int count = userMapper.insert(user);
		log.info("count={}", count);
		// INSERT INTO user (id, name, email, age, tenant_id) VALUES (17, '段绍齐',
		// '20620604@qq.com', 43, 1)
	}

	@Test
	public void updateByIdTest() {
		User user = new User();
		user.setId(RandomUtil.randomLong(1L, 100L + 1L));
		user.setAge(RandomUtil.randomInt(0, 100 + 1));
		user.setEmail(RandomUtil.randomNumbers(8) + "@qq.com");
		int count = userMapper.updateById(user);
		log.info("count={}", count);

		// UPDATE user SET email = '12071081@qq.com', age = 59 WHERE user.tenant_id =
		// tenant_id IN (1, 2) AND id = 27
	}

	@Test
	public void deleteByIdTest() {
		long id = RandomUtil.randomLong(1L, 100L + 1L);
		int count = userMapper.deleteById(id);
		log.info("count={}", count);
		// DELETE FROM user WHERE user.tenant_id = tenant_id IN (1, 2) AND id = 37
	}

	@Test
	public void selectListTest() {

		List<User> userList = userMapper
				.selectList(new QueryWrapper<User>().lambda().ge(User::getAge, 18).le(User::getAge, 30));
		userList.forEach(user -> {
			log.info(user.toString());
		});
		// SELECT id, gender, tenant_id, name, email, age FROM user WHERE user.tenant_id
		// = 1
		//SELECT id, gender, tenant_id, name, email, age FROM user WHERE (age >= 18 AND age <= 30) AND user.tenant_id = 1
	}

}
