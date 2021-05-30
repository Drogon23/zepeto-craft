
package com.zepeto.craft.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.zepeto.craft.**.mapper")
public class DataSourceConfiguration {

	@Bean
	@ConfigurationProperties(prefix = "datasource.h2")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		return createSqlSessionFactory(dataSource());
	}

	private SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/com/zepeto/craft/**/mapper/*Mapper.xml"));
		factory.setTypeAliasesPackage("com.zepeto.craft");

		SqlSessionFactory sqlSessionFactory = factory.getObject();
		sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);

		return factory.getObject();
	}
}
