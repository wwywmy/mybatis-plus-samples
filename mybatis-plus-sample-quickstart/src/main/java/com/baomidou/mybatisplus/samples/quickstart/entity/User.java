package com.baomidou.mybatisplus.samples.quickstart.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.samples.quickstart.enums.GenderEnum;

import lombok.Data;

@Data
@TableName(autoResultMap = true)
public class User {
	private Long id;
	private String name;
	private Integer age;
	private String email;
	private GenderEnum gender;
	private Long tenantId;
	@TableField(typeHandler = FastjsonTypeHandler.class)
	private Address address;
}
