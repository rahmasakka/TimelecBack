package com.timelec.timelec.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringConfiguration
{
	@Autowired
	private Environment env;
	
	
	@Bean
	public DataSource firstDataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}
	
	@Bean(name="timelec")
	public JdbcTemplate jdbcTemplateOne(@Qualifier("firstDataSource") DataSource ds)
	{
		return new JdbcTemplate(ds);
	}
	
	
	@Primary
	@Bean
	public DataSource secondDataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource2.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource2.url"));
		dataSource.setUsername(env.getProperty("spring.datasource2.username"));
		dataSource.setPassword(env.getProperty("spring.datasource2.password"));
		return dataSource;
	}

	@Bean(name="secondDataSource")
	public JdbcTemplate jdbcTemplateTwo(@Qualifier("secondDataSource") DataSource ds)
	{
		return new JdbcTemplate(ds);
	}
	
}