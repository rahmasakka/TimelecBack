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
	entityManagerFactoryRef = "testResultsP77EntityManagerFactory",
    transactionManagerRef = "testResultsP77TransactionManager", 
    basePackages = {"com.timelec.testResultsP77.repository"})

public class TestResultsP77DBConfig {
	@Bean(name = "testResultsP77DataSource")
	@ConfigurationProperties(prefix = "testresults77.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "testResultsP77EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean testResultsP77EntityManagerFactory(EntityManagerFactoryBuilder builder, 
																			@Qualifier("testResultsP77DataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.timelec.testResultsP77.models").persistenceUnit("testResultsP77").build();
	}
	
	@Bean(name = "testResultsP77TransactionManager")
	public PlatformTransactionManager testResultsP77TransactionManager(@Qualifier("testResultsP77EntityManagerFactory") EntityManagerFactory testResultsP77EntityManagerFactory) {
		return new JpaTransactionManager(testResultsP77EntityManagerFactory);
	}
}