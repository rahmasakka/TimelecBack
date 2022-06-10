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
	entityManagerFactoryRef = "gabarieEntityManagerFactory",
    transactionManagerRef = "gabarieTransactionManager", 
    basePackages = {"com.timelec.timelec.gabarie.repository"})


public class gabarieDBConfig {
	@Bean(name = "gabarieDataSource")
	@ConfigurationProperties(prefix = "gabarie.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "gabarieEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean gabarieEntityManagerFactory(EntityManagerFactoryBuilder builder, 
																			@Qualifier("gabarieDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.timelec.timelec.gabarie.model").persistenceUnit("gabarie").build();
	}
	
	@Bean(name = "gabarieTransactionManager")
	public PlatformTransactionManager gabarieTransactionManager(@Qualifier("gabarieEntityManagerFactory") EntityManagerFactory gabarieEntityManagerFactory) {
		return new JpaTransactionManager(gabarieEntityManagerFactory);
	}
}