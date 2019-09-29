package com.baomidou.mybatisplus.samples.quickstart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.samples.quickstart.entity.User;
import com.baomidou.mybatisplus.samples.quickstart.mapper.UserMapper;
import com.github.javafaker.Faker;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTest {

	@Resource
	private UserMapper userMapper;

	private Wrapper<User> getWrapper() {
		/*
		 * Wrapper<T> +--AbstractWrapper<T, R, Children extends AbstractWrapper<T, R,
		 * Children>> +--QueryWrapper<T> +--UpdateWrapper<T> +--AbstractLambdaWrapper<T,
		 * Children extends AbstractLambdaWrapper<T, Children>> +--LambdaQueryWrapper<T>
		 * +--LambdaUpdateWrapper<T>
		 */

//		QueryWrapper<User> wrapper = new QueryWrapper<User>();
//		//int id = RandomUtil.randomInt(1, 5 + 1);
//		//wrapper.eq("id", id);
//		
//		wrapper.eq("age", 18);
//		
//		
//		
//		wrapper.select("id","name");//查询只返回id/name字段
//		
//		wrapper.orderByAsc("age");//以age进行升序排序
//
//		return wrapper;

		Wrapper<User> wrapper = null;

		int x = RandomUtil.randomInt(1, 4 + 1);
		x = 7;
		switch (x) {
		case 1:
			log.info("普通查询");
			wrapper = plainQueryWrapper();
			break;
		case 2:
			log.info("lambda查询");
			wrapper = lambdaQueryWrapper();
			break;
		case 3:
			log.info("普通带子查询(sql注入)");
			wrapper = plainSqlInjectionWrapper();
			break;
		case 4:
			log.info("lambda带子查询(sql注入)");
			wrapper = lambdaSqlInjectionWrapper();
			break;
		case 5:
			log.info(" 带嵌套查询");
			wrapper = plainNestedWrapper();
			break;
		case 6:
			log.info(" lambda带嵌套查询");
			wrapper = lambdaNestedWrapper();
			break;
		case 7:
			log.info("自定义(sql注入) ");
			wrapper = plainCustomWrapper();
			break;
		case 8:
			log.info("sql ");
			wrapper = plainSqlWrapper();
			break;
			
		}

		return wrapper;

	}

	private Wrapper<User> plainQueryWrapper() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.eq("age", 18);

		return queryWrapper;
	}

	private Wrapper<User> lambdaQueryWrapper() {
		return new QueryWrapper<User>().lambda().eq(User::getAge, 18);
	}

	private Wrapper<User> plainSqlInjectionWrapper() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.inSql("age", "select age from user where id in (20,30)");

		return queryWrapper;
	}

	private Wrapper<User> lambdaSqlInjectionWrapper() {
		return new QueryWrapper<User>().lambda().inSql(User::getAge, "select age from user where id in (20,30)");
	}

	private Wrapper<User> plainNestedWrapper() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.nested(i -> i.eq("id", 20L).or().eq("id", 21L)).and(i -> i.eq("age", 29));

		return queryWrapper;
	}

	private Wrapper<User> plainSqlWrapper() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.nested(i -> i.eq("id", 20L).or().eq("id", 21L)).and(i -> i.eq("age", 29));

		return queryWrapper;
	}

	private Wrapper<User> lambdaNestedWrapper() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.nested(i -> i.eq("id", 20L).or().eq("id", 21L)).and(i -> i.eq("age", 29));

		return new QueryWrapper<User>().lambda().nested(i -> i.eq(User::getId, 20L).or().eq(User::getId, 21L))
				.and(i -> i.eq(User::getAge, 29));
	}

	private Wrapper<User> plainCustomWrapper() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.and(i -> i.eq("1", 1)).nested(i -> i.and(j -> j.eq("email", "41836755@qq.com").eq("age", 74))
				.or(j -> j.eq("email", "51178015@qq.com").eq("age", 29)));

		return queryWrapper;
	}

	private Page<User> getPage() {
		Page<User> page = new Page<>(1, 3);

		return page;
	}

	@Test
	public void deleteTest() {
		Wrapper<User> wrapper = getWrapper();
		int count = userMapper.delete(wrapper);
		log.info("count={}", count);
	}

	@Test
	public void deleteBatchIdsTest() {
		List<Long> idList = new ArrayList<Long>();
		for (long i = 1; i < 10L; i++) {
			idList.add(i);
		}

		int count = userMapper.deleteBatchIds(idList);
		log.info("count={}", count);
	}

	@Test
	public void deleteByMapTest() {
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("age", 0);

		int count = userMapper.deleteByMap(columnMap); // DELETE FROM user WHERE age = ?
		log.info("count={}", count);
	}

	@Test
	public void insertTest() {
		User user = new User();
		user.setId(RandomUtil.randomLong(1L, 100L + 1L));
		user.setId(RandomUtil.randomLong(1L, 5L + 1L));
		user.setName(new Faker(Locale.CHINA).name().fullName());
		user.setAge(RandomUtil.randomInt(0, 100 + 1));
		user.setEmail(RandomUtil.randomNumbers(8) + "@qq.com");

		int count = userMapper.insert(user);
		log.info("count={}", count);
	}

	@Test
	public void selectBatchIdsTest() {
		List<Long> idList = new ArrayList<Long>();
		for (long i = 1; i < 10L; i++) {
			idList.add(i);
		}

		List<User> userList = userMapper.selectBatchIds(idList);

		userList.forEach(e -> log.info(e.toString()));
	}

	@Test
	public void selectByIdTest() {
		Long id = RandomUtil.randomLong(1L, 100L + 1L);
		User user = userMapper.selectById(id);
		log.info("user={}", user);
	}

	@Test
	public void selectByMapTest() {
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("age", RandomUtil.randomInt(1, 100 + 1));

		List<User> userList = userMapper.selectByMap(columnMap); //
		userList.forEach(e -> log.info(e.toString()));
	}

	@Test
	public void selectCountTest() {
		Wrapper<User> wrapper = getWrapper();
		int count = userMapper.selectCount(wrapper);
		log.info("count={}", count);
	}

	@Test
	public void selectListTest() {
		Wrapper<User> wrapper = getWrapper();
		List<User> userList = userMapper.selectList(wrapper);
		userList.forEach(e -> log.info(e.toString()));
	}

	@Test
	public void selectMapsTest() {
		Wrapper<User> wrapper = getWrapper();
		List<Map<String, Object>> mapList = userMapper.selectMaps(wrapper);
		mapList.forEach(e -> log.info(e.toString()));
	}

	@Test
	public void selectMapsPageTest() {
		IPage<User> page = getPage();
		Wrapper<User> wrapper = getWrapper();

		IPage<Map<String, Object>> mapPage = userMapper.selectMapsPage(page, wrapper);
		log.info("mapPage={}", mapPage);
		mapPage.getRecords().forEach(e -> log.info(e.toString()));
	}

	@Test
	public void selectObjsTest() {
		Wrapper<User> wrapper = getWrapper();

		List<Object> objList = userMapper.selectObjs(wrapper);
		objList.forEach(e -> log.info(e.toString()));
	}

	@Test
	public void selectOneTest() {
		Wrapper<User> wrapper = getWrapper();

		User user = userMapper.selectOne(wrapper);
		log.info("user={}", user);
	}

	@Test
	public void selectPageTest() {
		IPage<User> page = getPage();
		Wrapper<User> wrapper = getWrapper();

		IPage<User> userPage = userMapper.selectPage(page, wrapper);
		log.info("userPage={}", userPage);
	}

	@Test
	public void update() {
		User user = new User();
		// user.setId(RandomUtil.randomLong(1L, 100L + 1L));
		user.setName(new Faker(Locale.CHINA).name().fullName());
		user.setAge(RandomUtil.randomInt(0, 100 + 1));
		user.setEmail(RandomUtil.randomNumbers(8) + "@qq.com");

		UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
		Long id = RandomUtil.randomLong(1L, 100L + 1L);
		updateWrapper.eq("id", id);

		User userOriginal = userMapper.selectById(id);
		log.info("userOriginal={}", userOriginal);

		int count = userMapper.update(user, updateWrapper);
		log.info("count={}", count);

		User userUpdate = userMapper.selectById(id);
		log.info("userUpdate={}", userUpdate);
	}

	@Test
	public void updateById() {
		Long id = RandomUtil.randomLong(1L, 100L + 1L);

		User user = new User();
		user.setId(id);
		user.setName(new Faker(Locale.CHINA).name().fullName());
		user.setAge(RandomUtil.randomInt(0, 100 + 1));
		user.setEmail(RandomUtil.randomNumbers(8) + "@qq.com");

		User userOriginal = userMapper.selectById(id);
		log.info("userOriginal={}", userOriginal);

		int count = userMapper.updateById(user);
		log.info("count={}", count);

		User userUpdate = userMapper.selectById(id);
		log.info("userUpdate={}", userUpdate);
	}

}
