package com.baomidou.mybatisplus.samples.quickstart.methods;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

public class DeleteAll extends AbstractMethod {

	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		/* 执行 SQL ，动态 SQL 参考类 SqlMethod */
        String sql = "delete from " + tableInfo.getTableName();
        /* mapper 接口方法名一致 */
        String method = "deleteAll";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addDeleteMappedStatement(mapperClass, method, sqlSource);
	}

}
