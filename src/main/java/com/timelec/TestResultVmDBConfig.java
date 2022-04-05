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
	entityManagerFactoryRef = "ancienEntityManagerFactory",
    transactionManagerRef = "ancienTransactionManager", 
    basePackages = {"com.timelec.testResultVm.repository"})


public class TestResultVmDBConfig {
	@Bean(name = "ancienDataSource")
	@ConfigurationProperties(prefix = "ancien.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "ancienEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean ancienEntityManagerFactory(EntityManagerFactoryBuilder builder, 
																			@Qualifier("ancienDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.timelec.testResultVm.models").persistenceUnit("ancien").build();
	}
	
	@Bean(name = "ancienTransactionManager")
	public PlatformTransactionManager ancienTransactionManager(@Qualifier("ancienEntityManagerFactory") EntityManagerFactory ancienEntityManagerFactory) {
		return new JpaTransactionManager(ancienEntityManagerFactory);
	}
}