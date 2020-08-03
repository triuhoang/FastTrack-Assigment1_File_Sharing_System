package com.fsoft.filesharingbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.fsoft.filesharingbackend.repository",
        entityManagerFactoryRef = FileSharingDbConfig.DB_ENTITY_MANAGER_FACTORY,
        transactionManagerRef = FileSharingDbConfig.DB_TRANSACTION_MANAGER)
public class FileSharingDbConfig {
    public static final String DB_ENTITY_MANAGER_FACTORY = "dbEntityManagerFactory";
    public static final String DB_TRANSACTION_MANAGER = "dbTransactionManager";

    private final Environment env;

    @Autowired
    public FileSharingDbConfig(Environment env) {
        this.env = env;
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource")
    public DataSourceProperties dbDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource dbDataSource() {
        DataSourceProperties primaryDataSourceProperties = dbDataSourceProperties();
        return DataSourceBuilder.create().driverClassName(primaryDataSourceProperties.getDriverClassName())
                .url(primaryDataSourceProperties.getUrl()).username(primaryDataSourceProperties.getUsername())
                .password(primaryDataSourceProperties.getPassword()).build();
    }

    @Bean(DB_TRANSACTION_MANAGER)
    @Primary
    public PlatformTransactionManager dbTransactionManager() {
        EntityManagerFactory factory = dbEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean(DB_ENTITY_MANAGER_FACTORY)
    @Primary
    public LocalContainerEntityManagerFactoryBean dbEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dbDataSource());
        factory.setPackagesToScan(new String[]{"com.fsoft.filesharingbackend.entity"});
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("jpa.show-sql"));
        factory.setJpaProperties(jpaProperties);

        return factory;

    }

}
