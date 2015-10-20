package ua.com.findcoach.conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;
import ua.com.findcoach.utils.EmailValidator;
import ua.com.findcoach.utils.CoachStatusHolder;
import ua.com.findcoach.utils.PadawanStatusHolder;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

/**
 * Created by DENIS on 26.09.2015.
 */
@Configuration
@ComponentScan({"ua.com.findcoach.controllers", "ua.com.findcoach.services"})
@PropertySource({"classpath:jdbc.properties"})
@EnableWebMvc
@EnableJpaRepositories("ua.com.findcoach.repository")
public class ApplicationConfiguration {
    @Autowired
    Environment environment;

    @Bean
    public ComboPooledDataSource dataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(environment.getProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setUser(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
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
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();


        return factory.getObject();
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
//                setProperty("hibernate.globally_quoted_identifiers", "true");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.hbm2ddl.auto", "validate");
            }
        };
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

    @Bean
    public CoachStatusHolder statusHolder(){return new CoachStatusHolder();}

    @Bean
    public PadawanStatusHolder PadawanStatusHolder(){return new PadawanStatusHolder();}

    @Bean
    public VelocityConfigurer velocityConfig() {
        VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
        velocityConfigurer.setResourceLoaderPath("WEB-INF/templates/");
        Properties props = new Properties();
        props.setProperty("input.encoding", "UTF-8");
        props.setProperty("output.encoding", "UTF-8");
        velocityConfigurer.setVelocityProperties(props);
        return velocityConfigurer;
    }

    @Bean
    public VelocityViewResolver velocityViewResolver() {
        VelocityViewResolver velocityViewResolver = new VelocityViewResolver();
        velocityViewResolver.setCache(false);
        velocityViewResolver.setPrefix("");
        velocityViewResolver.setSuffix(".html");
        velocityViewResolver.setContentType("text/html; charset=UTF-8");
        return velocityViewResolver;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
