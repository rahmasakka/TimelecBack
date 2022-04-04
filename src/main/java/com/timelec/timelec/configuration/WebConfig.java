//package com.timelec.timelec.configuration;
//
//import java.util.HashMap;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//		entityManagerFactoryRef = "userEntityManagerFactory", 
//		transactionManagerRef = "userTransactionManager", 
//		basePackages = {"com.timelec.timelec.repository" })
//public class WebConfig {
//
//	@Bean(name = "userDataSource")
//	@ConfigurationProperties(prefix = "spring.user.datasource")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean(name = "userEntityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(EntityManagerFactoryBuilder builder,
//			@Qualifier("userDataSource") DataSource dataSource) {
//		HashMap<String, Object> properties = new HashMap<>();
//		properties.put("hibernate.hbm2ddl.auto", "update");
//		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//		return builder.dataSource(dataSource).properties(properties)
//				.packages("com.timelec.timelec.models").persistenceUnit("User").build();
//	}
//
//	@Bean(name = "userTransactionManager")
//	public PlatformTransactionManager userTransactionManager(
//			@Qualifier("userEntityManagerFactory") EntityManagerFactory userEntityManagerFactory) {
//		return new JpaTransactionManager(userEntityManagerFactory);
//	}
//}
