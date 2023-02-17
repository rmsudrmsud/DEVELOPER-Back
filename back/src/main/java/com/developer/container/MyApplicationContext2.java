package com.developer.container;
//스프링용 설정클래스를 대신할 자바클래스

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration // 스프링용 설정클래스를 대신할 자바클래스이기때문에 어노테이션

public class MyApplicationContext2 {

	@Bean
	public HikariConfig hikariConfig() {
		// 커넥션풀 설정
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		config.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:xe");
		config.setUsername("dev");
		config.setPassword("dev");
		config.setMinimumIdle(3);

		return config;
	}

	@Bean
	public HikariDataSource dataSourceHikari() {
		return new HikariDataSource(hikariConfig());
	}

}
