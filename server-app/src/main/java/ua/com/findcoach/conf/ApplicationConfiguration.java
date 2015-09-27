package ua.com.findcoach.conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.com.findcoach.controllers.EmailValidator;

import javax.persistence.EntityManagerFactory;

/**
 * Created by DENIS on 26.09.2015.
 */
@Configuration
@ComponentScan({"ua.com.findcoach.controllers"})
@EnableWebMvc
public class ApplicationConfiguration {

    @Bean
    public ComboPooledDataSource dataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("org.postgresql.Driver");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/findcoach");
        dataSource.setUser("postgres");
        dataSource.setPassword("adidas");
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() throws Exception {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("ua.com.findcoach.domain");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();


        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }


    @Bean
    public EmailValidator emailValidator() {
        return new EmailValidator();
    }
}
