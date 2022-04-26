package com.timelec;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	entityManagerFactoryRef = "testResultsFuserblocEntityManagerFactory",
    transactionManagerRef = "testResultsFuserblocTransactionManager", 
    basePackages = {"com.timelec.testResultsFuserbloc.repository"})


public class TestResultsFuserblocDBConfig {
	@Bean(name = "testResultsFuserblocDataSource")
	@ConfigurationProperties(prefix = "testresultsfuserbloc.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "testResultsFuserblocEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean testResultsFuserblocEntityManagerFactory(EntityManagerFactoryBuilder builder, 
																			@Qualifier("testResultsFuserblocDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.timelec.testResultsFuserbloc.models").persistenceUnit("testResultsFuserbloc").build();
	}
	
	@Bean(name = "testResultsFuserblocTransactionManager")
	public PlatformTransactionManager testResultsFuserblocTransactionManager(@Qualifier("testResultsFuserblocEntityManagerFactory") EntityManagerFactory testResultsFuserblocEntityManagerFactory) {
		return new JpaTransactionManager(testResultsFuserblocEntityManagerFactory);
	}
}