//package com.timelec.timelec.configuration;
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
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import com.timelec.timelec.models.Role;
//import com.timelec.timelec.repository.RoleRepository;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackageClasses = RoleRepository.class, 
//					   entityManagerFactoryRef = "roleDSEmFactory", 
//					   transactionManagerRef = "roleDSTransactionManager")
//public class RoleDBConfig {
//	
//	@Bean
//	@ConfigurationProperties("spring.datasource1")
//	public DataSourceProperties roleDSProperties() {
//		return new DataSourceProperties();
//	}
//	
//	@Bean
//	public DataSource roleDS(@Qualifier("roleDSProperties") DataSourceProperties roleDSProperties) {
//		return roleDSProperties.initializeDataSourceBuilder().build();
//	}
//	
//	@Bean
//	public LocalContainerEntityManagerFactoryBean roleDSEmFactory(@Qualifier("roleDS") DataSource roleDS, EntityManagerFactoryBuilder builder) {
//		return builder.dataSource(roleDS).packages(Role.class).build();
//	}
//	
//	@Bean
//	public PlatformTransactionManager roleDSTransactionManager(EntityManagerFactory roleDSEmFactory) {
//		return new JpaTransactionManager(roleDSEmFactory);
//	}
//}