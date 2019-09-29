package com.baomidou.mybatisplus.samples.quickstart.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("student")
public class Student {
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	private String name;
	private Integer age;

}
