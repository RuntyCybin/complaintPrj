package com.cybin.complaintsystem;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // it is where we insert or pool something from or to the database
@PropertySource ({"classpath:database-properties.properties"}) // this is where we tell spring where we take the properties from
@ComponentScan ({"com.cybin.complaintsystem"}) // package that we are going to scan for DAO classes
public class PersistenceConfig {
	
	@Autowired
	private Environment env;
	
	Properties hibernateConfig() {
		Properties props = new Properties();
		props.setProperty("hibernate.hdm2ddl.auto", env.getProperty("hibernate.hdm2ddl.auto"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		return props;
	}
	
	// create a Datasource (it's where we get the data from)
	@Bean
	public DataSource datasource () {
		
		BasicDataSource basicds = new BasicDataSource();
		basicds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		basicds.setUrl(env.getProperty("jdbc.url"));
		basicds.setUsername(env.getProperty("jdbc.user"));
		basicds.setPassword(env.getProperty("jdbc.password"));
		
		return basicds;
	}
	
	@Bean
	LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFact = new LocalSessionFactoryBean();
		
		sessionFact.setDataSource(this.datasource());
		sessionFact.setPackagesToScan(new String[] {"com.cybin.complaintsystem.entities"}); // packages that are going to be scanned for our hibernate entities
		sessionFact.setHibernateProperties(hibernateConfig());
		
		return sessionFact;
	}
	
	// it manages our transactions
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFact) {
		HibernateTransactionManager transactionMan = new HibernateTransactionManager();
		transactionMan.setSessionFactory(sessionFact);
		return transactionMan;
	}
	
}
