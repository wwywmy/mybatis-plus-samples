package com.baomidou.mybatisplus.samples.quickstart.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
@KeySequence("seq_course")
public class Course {
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;
	private String name;
}
