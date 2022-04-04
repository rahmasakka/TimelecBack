//package com.timelec.timelec.configuration;
// 
//
//import java.util.HashMap;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//
//@Configuration
//@PropertySource({"classpath:application.properties"})
//@EnableJpaRepositories(
//    basePackages = "com.timelec.timelec.repository.ProductionRepository", 
//    entityManagerFactoryRef = "productEntityManagerFactory",
//    transactionManagerRef = "productTransactionManager")
//
//public class ProductionDBConfig {
//
//	
//	
//	@Autowired(required = false)
//    private Environment env;
//	
//	
//    @Bean
//    @ConfigurationProperties(prefix="spring.second-datasource")
//    public DataSource productDataSource() {
//        return DataSourceBuilder.create().build();
//    } 
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean productEntityManager() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(productDataSource());
//        em.setPackagesToScan(new String[] { "com.timelec.timelec.models.Summary" });
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
//    /*
//    @Bean
//    public DataSource productDataSource() {
// 
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
//        dataSource.setUrl(env.getProperty("product.jdbc.url"));
//        dataSource.setUsername(env.getProperty("jdbc.user"));
//        dataSource.setPassword(env.getProperty("jdbc.pass"));
//
//        return dataSource;
//    }*/
//
//    @Bean
//    public PlatformTransactionManager productTransactionManager(
//     @Qualifier("productEntityManagerFactory") EntityManagerFactory productEntityManagerFactory) {
//     return new JpaTransactionManager(productEntityManagerFactory);
//    }
//}