package com.baomidou.mybatisplus.samples.quickstart.enums;

import java.util.Arrays;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderEnum {

	MALE("M", "男"),

	FEMALE("F", "女"),

	UNKONWN("U", "未知");

	@EnumValue
	private String value;
	private String text;

	GenderEnum(String value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static GenderEnum getEnum(String value) {
		return Arrays.asList(GenderEnum.values()).stream().filter(e -> e.getValue().equals(value)).findFirst().get();
	}
}
