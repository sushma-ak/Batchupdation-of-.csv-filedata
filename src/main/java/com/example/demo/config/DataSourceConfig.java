package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties(prefix="spring.datasource")
public class DataSourceConfig {
	
	private String username;
	private String password;
	private String url;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Bean(name="dataSource")
	public DataSource datasource()
	{
		HikariDataSource ds=new HikariDataSource();
		ds.setJdbcUrl(this.getUrl());
		ds.setPassword(this.getPassword());
		ds.setUsername(this.getUsername());
		return ds;
		
	}
	@Bean(name="transactionManager")
	public DataSourceTransactionManager datasourceTransactionManager()
	{
		return new DataSourceTransactionManager(datasource());
		
	}
	
	@Bean(name="jdbcTemplate")
	public JdbcTemplate jdbcTemplate()
	{
		return new JdbcTemplate(datasource());
		
	}
}
