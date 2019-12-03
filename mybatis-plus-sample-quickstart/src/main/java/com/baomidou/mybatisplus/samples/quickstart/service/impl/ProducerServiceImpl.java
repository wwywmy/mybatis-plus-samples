package com.baomidou.mybatisplus.samples.quickstart.service.impl;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.samples.quickstart.entity.User;
import com.baomidou.mybatisplus.samples.quickstart.service.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService {
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	/**
	 * 将User对象转换成Message对象，并发送到queue或者topic
	 *
	 * @param destination
	 * @param user
	 * @return
	 */
	@Override
	public boolean produce(Destination destination, User user) {
		jmsMessagingTemplate.convertAndSend(destination, user);
		return true;
	}
}
