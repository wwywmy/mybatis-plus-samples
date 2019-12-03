package com.baomidou.mybatisplus.samples.quickstart.service.impl;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.samples.quickstart.entity.User;
import com.baomidou.mybatisplus.samples.quickstart.service.ConsumerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {
	@Override
	@JmsListener(destination = "item.queue", containerFactory = "queueContainerFactory")
	public void consumeQueue(User user) {
		log.info(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
	}

	@Override
	@JmsListener(destination = "chen.topic", containerFactory = "topicContainerFactory")
	public void consumeTopic(User user) {
		log.info(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
	}
}
