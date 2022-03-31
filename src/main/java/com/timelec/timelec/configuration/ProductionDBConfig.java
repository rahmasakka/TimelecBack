/*package com.timelec.timelec.configuration;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
@EnableJpaRepositories(entityManagerFactoryRef = "productionEntityManagerFactory", 
					   transactionManagerRef = "productionTransactionManager", 
					   basePackages = {"com.timelec.timelec.repository.ProductionRepository" })
public class ProductionDBConfig {

	@Bean(name = "productionDataSource")
	@ConfigurationProperties(prefix = "spring.production.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "productionEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean productionEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("productionDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.timelec.timelec.models.Summary").persistenceUnit("Production").build();
	}

	@Bean(name = "productionTransactionManager")
	public PlatformTransactionManager productionTransactionManager(
			@Qualifier("productionEntityManagerFactory") EntityManagerFactory productionEntityManagerFactory) {
		return new JpaTransactionManager(productionEntityManagerFactory);
	}
}*/