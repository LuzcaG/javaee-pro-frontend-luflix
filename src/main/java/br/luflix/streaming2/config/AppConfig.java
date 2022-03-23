package br.luflix.streaming2.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class AppConfig {
	//configurando a conexão da aplicação ao Banco de dados MySql
		@Bean
		public DataSource dataSource() {
			DriverManagerDataSource dataSourse = new DriverManagerDataSource();
			dataSourse.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSourse.setUrl("jdbc:mysql://localhost:3307/bc_luflix");
			dataSourse.setUsername("root");
			dataSourse.setPassword("root");
			return dataSourse;
		}
		//configura o hibernate (ORM - Mapeamento objeto Relacional)
		@Bean
		public JpaVendorAdapter jpaVendorAdapter() {
			HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
			adapter.setDatabase(Database.MYSQL);
			adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
			adapter.setShowSql(true);
			adapter.setPrepareConnection(true);
			adapter.setGenerateDdl(true);
			return adapter;
		}
}
