package com.baomidou.mybatisplus.samples.quickstart.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.samples.quickstart.enums.GenderEnum;

import lombok.Data;

@Data
@TableName(autoResultMap = true)
public class User implements Serializable{
	private static final long serialVersionUID = 3981097349922510021L;
	
	private Long id;
	private String name;
	private Integer age;
	private String email;
	private GenderEnum gender;
	private Long tenantId;
	@TableField(typeHandler = FastjsonTypeHandler.class)
	private Address address;
	@Version
	private Integer version;
	@TableLogic
    private Integer isDelete;
}
