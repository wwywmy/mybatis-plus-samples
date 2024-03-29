package com.baomidou.mybatisplus.samples.quickstart.config;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;

/**
 * @author miemie
 * @since 2018-08-10
 */
@Configuration
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class MybatisPlusConfig {

	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}

	/**
	 * 分页插件
	 */
//	@Bean
//	public PaginationInterceptor paginationInterceptor() {
//		// 开启 count 的 join 优化,只针对 left join !!!
//		return new PaginationInterceptor().setCountSqlParser(new JsqlParserCountOptimize(true));
//	}

	/**
	 * 多租户属于 SQL 解析部分，依赖 MP 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		/*
		 * 【测试多租户】 SQL 解析处理拦截器<br> 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（
		 * 注意观察 SQL ）<br>
		 */
		List<ISqlParser> sqlParserList = new ArrayList<>();
		TenantSqlParser tenantSqlParser = new MyTenantParser();
		tenantSqlParser.setTenantHandler(new TenantHandler() {

			/**
			 * 2019-8-1
			 *
			 * https://gitee.com/baomidou/mybatis-plus/issues/IZZ3M
			 *
			 * tenant_id in (1,2)
			 *
			 * @return
			 */
			@Override
			public Expression getTenantId(boolean where) {
				final boolean multipleTenantIds = true;
				if (where && multipleTenantIds) {
					return multipleTenantIdCondition();
				} else {
					return singleTenantIdCondition();
				}
			}

			private Expression singleTenantIdCondition() {
				return new LongValue(1);// ID自己想办法获取到
			}

			private Expression multipleTenantIdCondition() {
				final InExpression inExpression = new InExpression();
				inExpression.setLeftExpression(new Column(getTenantIdColumn()));
				final ExpressionList itemsList = new ExpressionList();
				final List<Expression> inValues = new ArrayList<>(2);
				inValues.add(new LongValue(1));// ID自己想办法获取到
				inValues.add(new LongValue(2));
				itemsList.setExpressions(inValues);
				inExpression.setRightItemsList(itemsList);
				return inExpression;
			}

			@Override
			public String getTenantIdColumn() {
				return "tenant_id";
			}

			@Override
			public boolean doTableFilter(String tableName) {
				// 这里可以判断是否过滤表
				/*
				 * if ("user".equals(tableName)) { return true; }
				 */
//                return false;
				return !"user".equalsIgnoreCase(tableName);
			}

		});

		sqlParserList.add(tenantSqlParser);
		paginationInterceptor.setSqlParserList(sqlParserList);
//        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
//            @Override
//            public boolean doFilter(MetaObject metaObject) {
//                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
//                // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
//                if ("com.baomidou.springboot.mapper.UserMapper.selectListBySQL".equals(ms.getId())) {
//                    return true;
//                }
//                return false;
//            }
//        });
		return paginationInterceptor;
	}

//	/**
//	 * sequence主键，需要配置一个主键生成器 配合实体类注解 {@link KeySequence} + {@link TableId}
//	 * type=INPUT
//	 * 
//	 * @return
//	 */
//	@Bean
//	public IKeyGenerator keyGenerator() {
//		return new IKeyGenerator() {
//
//			@Override
//			public String executeSql(String incrementerName) {
////				return null;
//				long workerId = 1L; // 终端ID
//				long datacenterId = 1L;// 数据中心ID
//				log.info("workerId={}", workerId);
//				log.info("datacenterId={}", datacenterId);
//				String key = IdUtil.createSnowflake(workerId, datacenterId).nextIdStr();
//				log.info("key={}", key);
//				return "select " + key;
//
//			}
//		};
//	}
}
