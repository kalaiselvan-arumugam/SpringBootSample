package com.test.utility;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = {
		"com.jpa.repository" })
public class DataBaseUtility {
	
	ApplicationConfig configurer = null;
	Logger log = LogManager.getLogger(DataBaseUtility.class);
	public HikariDataSource hikariConfig() throws Exception {
		HikariDataSource dataSource = new HikariDataSource();
		HikariConfig hikariConfig = new HikariConfig();
		System.out.println("Password values is :  "+AESEncription.decrypt(ApplicationConfig.getTag("spring.datasource.password")));
		log.info("Password values is :  "+AESEncription.decrypt(ApplicationConfig.getTag("spring.datasource.password")));
		System.out.println("Password values is from file:  "+ApplicationConfig.getTag("spring.datasource.password"));
		log.info("Password values is from file is  :  "+ApplicationConfig.getTag("spring.datasource.password"));
		hikariConfig.setDriverClassName(ApplicationConfig.getTag("spring.datasource.driver-class-name"));
		hikariConfig.setJdbcUrl(ApplicationConfig.getTag("spring.datasource.url"));
		hikariConfig.setUsername(ApplicationConfig.getTag("spring.datasource.username"));
//		hikariConfig.setPassword(Cryptography.getPlainText(ApplicationConfig.getTag("P@ssw0rd")));
		hikariConfig.setPassword(AESEncription.decrypt(ApplicationConfig.getTag("spring.datasource.password")));
		hikariConfig.setMaximumPoolSize(Integer.parseInt(ApplicationConfig.getTag("MaxPoolSize"))); //10
		hikariConfig.setMinimumIdle(Integer.parseInt(ApplicationConfig.getTag("MinIdle")));
		hikariConfig.setPoolName("");
		hikariConfig.setAutoCommit(true);
		hikariConfig.setConnectionTimeout(Integer.parseInt(ApplicationConfig.getTag("DBConnectionTimeOut")));
		hikariConfig.setLeakDetectionThreshold(60 * 3 * 1000);
		try {
			log.info("DB Status : Connecting");
			dataSource = new HikariDataSource(hikariConfig);
			log.info("DB Status : Success");
		} catch (Exception exception) {
			log.info("DB Status : Failure");
			log.info("Getting exception in connection database {} " , GetStackTrace.getMessage(exception));
		}

		return dataSource;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(hikariConfig());
		entityManagerFactoryBean.setJpaProperties(hibProperties());
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		entityManagerFactoryBean.setPackagesToScan("com.jpa.domain");
		return entityManagerFactoryBean;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		String dialect = "org.hibernate.dialect.SQLServer2012Dialect";
		properties.put("hibernate.dialect", dialect);
		return properties;
	}

	@Bean(name = "transactionManager")
	JpaTransactionManager transactionManager() throws Exception {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}
