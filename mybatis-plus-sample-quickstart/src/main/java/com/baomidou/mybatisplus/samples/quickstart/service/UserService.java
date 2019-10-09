package com.baomidou.mybatisplus.samples.quickstart.service;

import java.util.List;

import com.baomidou.mybatisplus.samples.quickstart.entity.User;

public interface UserService {
	List<User> selectList();
}
