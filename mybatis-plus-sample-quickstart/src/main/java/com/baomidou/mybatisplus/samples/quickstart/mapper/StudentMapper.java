package com.baomidou.mybatisplus.samples.quickstart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.samples.quickstart.entity.Student;

public interface StudentMapper extends BaseMapper<Student> {
	void deleteAll();
}
