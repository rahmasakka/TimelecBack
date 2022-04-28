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
	entityManagerFactoryRef = "testResultsDevP87EntityManagerFactory",
    transactionManagerRef = "testResultsDevP87TransactionManager", 
    basePackages = {"com.timelec.timelec.p87.repository"})



public class TestResultsDevP87DBConfig {
	@Bean(name = "testResultsDevP87DataSource")
	@ConfigurationProperties(prefix = "testresults87.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "testResultsDevP87EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean testResultsDevP87EntityManagerFactory(EntityManagerFactoryBuilder builder, 
																			@Qualifier("testResultsDevP87DataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.timelec.timelec.p87.model").persistenceUnit("testResultsDevP87").build();
	}
	
	@Bean(name = "testResultsDevP87TransactionManager")
	public PlatformTransactionManager testResultsDevP87TransactionManager(@Qualifier("testResultsDevP87EntityManagerFactory") EntityManagerFactory testResultsDevP87EntityManagerFactory) {
		return new JpaTransactionManager(testResultsDevP87EntityManagerFactory);
	}
}