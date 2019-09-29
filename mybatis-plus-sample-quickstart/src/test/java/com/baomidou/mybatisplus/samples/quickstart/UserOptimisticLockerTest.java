package com.baomidou.mybatisplus.samples.quickstart;

import java.util.Locale;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.samples.quickstart.entity.User;
import com.baomidou.mybatisplus.samples.quickstart.mapper.UserMapper;
import com.github.javafaker.Faker;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserOptimisticLockerTest {
	@Resource
	private UserMapper userMapper;

	@Test
	public void insertTest() {

		Long id = RandomUtil.randomLong(10L, 20L + 1L);
		log.info("id={}", id);
		User user = new User();
		user.setId(id);
		user.setName(new Faker(Locale.CHINA).name().fullName());
		user.setAge(RandomUtil.randomInt(0, 100 + 1));
		user.setEmail(RandomUtil.randomNumbers(8) + "@qq.com");
		user.setVersion(1);
		int count = userMapper.insert(user);
		log.info("count={}", count);

		User userUpdate = new User();
		userUpdate.setId(id);
		userUpdate.setAge(18);
		userUpdate.setVersion(1);
		Assert.assertEquals("Should update success", 1, userMapper.updateById(userUpdate));
		Assert.assertEquals("Should version = version+1", 2, userUpdate.getVersion().intValue());
	}

	@Test
	public void updateByIdFailTest() {
		Long id = RandomUtil.randomLong(10L, 20L + 1L);
		log.info("id={}", id);
		User user = new User();
		user.setId(id);
		user.setName(new Faker(Locale.CHINA).name().fullName());
		user.setAge(RandomUtil.randomInt(0, 100 + 1));
		user.setEmail(RandomUtil.randomNumbers(8) + "@qq.com");
		user.setVersion(1);
		int count = userMapper.insert(user);
		log.info("count={}", count);

		User userUpdate = new User();
		userUpdate.setId(id);
		userUpdate.setAge(18);
		userUpdate.setVersion(0);
		Assert.assertEquals("Should update failed due to incorrect version(actually 1, but 0 passed in)", 0,
				userMapper.updateById(userUpdate));
	}

	@Test
	public void updateByIdNoVersionTest() {
		Long id = RandomUtil.randomLong(10L, 20L + 1L);
		log.info("id={}", id);
		User user = new User();
		user.setId(id);
		user.setName(new Faker(Locale.CHINA).name().fullName());
		user.setAge(RandomUtil.randomInt(0, 100 + 1));
		user.setEmail(RandomUtil.randomNumbers(8) + "@qq.com");
		user.setVersion(1);
		int count = userMapper.insert(user);
		log.info("count={}", count);

		User userUpdate = new User();
		userUpdate.setId(id);
		userUpdate.setAge(19);
		userUpdate.setVersion(null);
		Assert.assertEquals("Should update success as no version passed in", 1, userMapper.updateById(userUpdate));
		User updated = userMapper.selectById(id);
		Assert.assertEquals("Version not changed", 1, updated.getVersion().intValue());
		Assert.assertEquals("Age updated", 19, updated.getAge().intValue());
	}
	
	@Test
    public void updateByEntityTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("version", 1);
        int count = userMapper.selectCount(queryWrapper);

        User entity = new User();
        entity.setAge(28);
        entity.setVersion(1);

        Assert.assertEquals("updated records should be same", count, userMapper.update(entity, null));
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("version", 1);
        Assert.assertEquals("No records found with version=1", 0, userMapper.selectCount(queryWrapper).intValue());
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("version", 2);
        Assert.assertEquals("All records with version=1 should be updated to version=2", count, userMapper.selectCount(queryWrapper).intValue());
    }
}
