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
	entityManagerFactoryRef = "testResultVMEntityManagerFactory",
    transactionManagerRef = "testResultVMTransactionManager", 
    basePackages = {"com.timelec.timelec.vm.repository"})


public class TestResultVmDBConfig {
	@Bean(name = "testResultVMDataSource")
	@ConfigurationProperties(prefix = "testresultvm.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "testResultVMEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean testResultVMEntityManagerFactory(EntityManagerFactoryBuilder builder, 
																			@Qualifier("testResultVMDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.timelec.timelec.vm.model").persistenceUnit("testResultVM").build();
	}
	
	@Bean(name = "testResultVMTransactionManager")
	public PlatformTransactionManager testResultVMTransactionManager(@Qualifier("testResultVMEntityManagerFactory") EntityManagerFactory testResultVMEntityManagerFactory) {
		return new JpaTransactionManager(testResultVMEntityManagerFactory);
	}
}