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
	entityManagerFactoryRef = "testResultsSircoSircoverEntityManagerFactory",
    transactionManagerRef = "testResultsSircoSircoverTransactionManager", 
    basePackages = {"com.timelec.timelec.sircoSircover.repository"})


public class TestResultsSircoSircoverDBConfig {
	@Bean(name = "testResultsSircoSircoverDataSource")
	@ConfigurationProperties(prefix = "testresultssircosircover.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "testResultsSircoSircoverEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean testResultsSircoSircoverEntityManagerFactory(EntityManagerFactoryBuilder builder, 
																			@Qualifier("testResultsSircoSircoverDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.timelec.timelec.sircoSircover.model").persistenceUnit("testResultsSircoSircover").build();
	}
	
	@Bean(name = "testResultsSircoSircoverTransactionManager")
	public PlatformTransactionManager testResultsSircoSircoverTransactionManager(@Qualifier("testResultsSircoSircoverEntityManagerFactory") EntityManagerFactory testResultsSircoSircoverEntityManagerFactory) {
		return new JpaTransactionManager(testResultsSircoSircoverEntityManagerFactory);
	}
}