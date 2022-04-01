//package com.timelec.timelec.configuration;
// 
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import com.timelec.timelec.models.Summary;
//
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackageClasses = Summary.class, 
//						basePackages = {"com.timelec.timelec.repository.ProductionRepository"},
//						entityManagerFactoryRef = "secondaryEntityManagerFactory",
//						transactionManagerRef = "secondaryTransactionManager")
//
//public class ProductionDBConfig {
//	
//	@Primary
//	@Bean//(name = "secondaryDataSourceProperties")
//	@ConfigurationProperties("spring.datasource2")
//	public DataSourceProperties productDSProperties() {
//		return new DataSourceProperties();
//	}
//	
//	@Primary
//	@Bean//(name = "secondaryDataSource")
//	public DataSource productDS(@Qualifier("productDSProperties") DataSourceProperties productDSProperties) {
//		return productDSProperties.initializeDataSourceBuilder().build();
//	}
//	
//	@Primary
//	@Bean//(name = "secondaryEntityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean productDSEmFactory(@Qualifier("productDS") DataSource productDS, EntityManagerFactoryBuilder builder) {
//		return builder.dataSource(productDS).packages(Summary.class).build();
//	}
//	
//	@Primary
//	@Bean//(name = "secondaryTransactionManager")
//	public PlatformTransactionManager productDSTransactionManager(EntityManagerFactory productDSEmFactory) {
//		return new JpaTransactionManager(productDSEmFactory);
//	}
//}