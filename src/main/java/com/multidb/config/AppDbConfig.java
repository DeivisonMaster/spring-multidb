package com.multidb.config;

import javax.sql.DataSource;

import com.multidb.repository.app.LivroRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(basePackageClasses = LivroRepository.class, entityManagerFactoryRef = "appEntityManager")
public class AppDbConfig {
	
	/**
	 * @description Define uma instancia de data source especifica ao banco app
	 * @author Deivison
	 * */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "app.datasource")
	public DataSource appDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * @description Sobrescreve instancia de EntityManager padrão do Spring para um data source especifico 
	 * @param builder define o datasource do tipo appDataSource e o pacote de classes de dominios relacionadas
	 * @author Deivison
	 * */
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean appEntityManager(EntityManagerFactoryBuilder builder, 
			@Qualifier("appDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.multidb.model.app").build();
	}
}
