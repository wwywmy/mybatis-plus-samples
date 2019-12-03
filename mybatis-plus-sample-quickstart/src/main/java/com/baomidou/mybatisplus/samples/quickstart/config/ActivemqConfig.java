package com.baomidou.mybatisplus.samples.quickstart.config;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@Configuration
@EnableJms
public class ActivemqConfig {
	/**
	 * 实现监听queue
	 *
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	public JmsListenerContainerFactory<?> queueContainerFactory(ConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setPubSubDomain(false);
		return factory;
	}

	/**
	 * 实现监听topic
	 *
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	public JmsListenerContainerFactory<?> topicContainerFactory(ConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setPubSubDomain(true);
		return factory;
	}
}
