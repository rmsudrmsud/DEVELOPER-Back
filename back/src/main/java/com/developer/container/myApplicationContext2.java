package com.developer.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration

public class myApplicationContext2 {
	
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		config.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:xe");
		config.setUsername("spring");
		config.setPassword("spring");
		config.setMinimumIdle(3);
		return config;
	}
	
	@Bean
	public HikariDataSource dataSourceHikari() {
		return new HikariDataSource(hikariConfig());
	}
	
//	//AOP에 의한 @transactional 쓰려고
//	@Bean
//	public DataSourceTransactionManager transactionManager() {
//		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
//		dstm.setDataSource(dataSourceHikari());
//		return dstm;
//	}
}
