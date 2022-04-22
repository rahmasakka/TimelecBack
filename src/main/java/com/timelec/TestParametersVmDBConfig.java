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
	entityManagerFactoryRef = "testParametersVMEntityManagerFactory",
    transactionManagerRef = "testParametersVMTransactionManager", 
    basePackages = {"com.timelec.testParametersVM.repository"})


public class TestParametersVmDBConfig {
	@Bean(name = "testParametersVMDataSource")
	@ConfigurationProperties(prefix = "testparametersvm.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "testParametersVMEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean testParametersVMEntityManagerFactory(EntityManagerFactoryBuilder builder, 
																			@Qualifier("testParametersVMDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.timelec.testParametersVM.models").persistenceUnit("testParametersVM").build();
	}
	
	@Bean(name = "testParametersVMTransactionManager")
	public PlatformTransactionManager testParametersVMTransactionManager(@Qualifier("testParametersVMEntityManagerFactory") EntityManagerFactory testParametersVMEntityManagerFactory) {
		return new JpaTransactionManager(testParametersVMEntityManagerFactory);
	}
}