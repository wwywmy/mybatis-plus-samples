package com.baomidou.mybatisplus.samples.quickstart.service;

import java.util.Locale;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.samples.quickstart.entity.User;
import com.github.javafaker.Faker;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProducerServiceTest {

	@Autowired
	private ProducerService producerService;

	@Test
	public void produceTest() {

		Destination destination = new ActiveMQQueue("item.queue");

		User user = new User();
		user.setId(IdUtil.getSnowflake(1L, 1L).nextId());
		user.setName(new Faker(Locale.CHINA).name().fullName());
		user.setAge(RandomUtil.randomInt(1, 100));

		producerService.produce(destination, user);
	}
	
	@Test
	public void consumeTopicTest() {
		Destination destination = new ActiveMQTopic("chen.topic");

		User user = new User();
		user.setId(IdUtil.getSnowflake(1L, 1L).nextId());
		user.setName(new Faker(Locale.CHINA).name().fullName()+"_TOPIC");
		user.setAge(RandomUtil.randomInt(1, 100));
		user.setTenantId(IdUtil.getSnowflake(1L, 8L).nextId());

		producerService.produce(destination, user);
	}
}
