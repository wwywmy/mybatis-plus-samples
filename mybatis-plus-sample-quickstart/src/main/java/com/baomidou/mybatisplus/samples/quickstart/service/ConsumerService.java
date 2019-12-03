package com.baomidou.mybatisplus.samples.quickstart.service;

import com.baomidou.mybatisplus.samples.quickstart.entity.User;

public interface ConsumerService {

	void consumeQueue(User user);

	void consumeTopic(User user);

}
