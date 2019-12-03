package com.baomidou.mybatisplus.samples.quickstart.service;

import javax.jms.Destination;

import com.baomidou.mybatisplus.samples.quickstart.entity.User;

public interface ProducerService {

	boolean produce(Destination destination, User user);

}
