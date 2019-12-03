package com.baomidou.mybatisplus.samples.quickstart.service;

import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.samples.quickstart.entity.User;
import com.github.javafaker.Faker;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisServiceTest {
	@Autowired
	private RedisTemplate<String, String> strRedisTemplate;
	@Autowired
	private RedisTemplate<String, Serializable> serializableRedisTemplate;

	@Test
	public void stringTest() {
		String key = IdUtil.getSnowflake(1L, 1L).nextIdStr();
		String value = new Faker(Locale.CHINA).name().fullName();
		
		log.info("key={},value={}",key,value);
		
		strRedisTemplate.opsForValue().set(key, value);
		log.info("key={},valueFromRedis={}",key,strRedisTemplate.opsForValue().get(key));
	}

	@Test
	public void serializableTest() {
		User user = new User();
		user.setId(IdUtil.getSnowflake(1L, 1L).nextId());
		user.setName(new Faker(Locale.CHINA).name().fullName()+"_REDIS");
		user.setAge(RandomUtil.randomInt(1, 100));
		user.setTenantId(IdUtil.getSnowflake(1L, 8L).nextId());
		log.info(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
		serializableRedisTemplate.opsForValue().set(Long.toString(user.getId()), user);
		
		User userFromRedis = (User) serializableRedisTemplate.opsForValue().get(Long.toString(user.getId()));
		log.info(ToStringBuilder.reflectionToString(userFromRedis, ToStringStyle.MULTI_LINE_STYLE));
	}

}
