package com.baomidou.mybatisplus.samples.quickstart;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
public class UserDeleteTest {

	@Resource
	private UserMapper userMapper;

	@Test
	public void insertTest() {

		Long id = RandomUtil.randomLong(20L, 30L + 1L);
		log.info("id={}", id);
		User user = new User();
		user.setId(id);
		user.setName(new Faker(Locale.CHINA).name().fullName());
		user.setAge(RandomUtil.randomInt(0, 100 + 1));
		user.setEmail(RandomUtil.randomNumbers(8) + "@qq.com");
		user.setVersion(1);
		int count = userMapper.insert(user);
		log.info("count={}", count);

	}

	@Test
	public void deleteTest() {
		Long id = 29L;
		int count = userMapper.deleteById(id);
		log.info("count={}", count);
	}

	@Test
	public void deleteByWrapperTest() {
		int count = userMapper.delete(new QueryWrapper<User>().eq("age", 18));
		log.info("count={}", count);
	}

	@Test
	public void deleteBatchIdsTest() {
		int count = userMapper.deleteBatchIds(Arrays.asList(31L, 32L, 33L, 34L));
		log.info("count={}", count);
	}

	@Test
	public void deleteByMapTest() {
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("age", 18);
		columnMap.put("gender", "F");
		int count = userMapper.deleteByMap(columnMap);
		log.info("count={}", count);

		// UPDATE user SET is_delete = 1 WHERE user.tenant_id = tenant_id IN (1, 2) AND
		// gender = 'F' AND age = 18 AND is_delete = 0
	}
}
