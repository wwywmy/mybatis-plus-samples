package com.baomidou.mybatisplus.samples.quickstart.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Teacher {
	@TableId(value = "id", type = IdType.ID_WORKER)
	private Long id;
	private String no;
	private String name;
}
