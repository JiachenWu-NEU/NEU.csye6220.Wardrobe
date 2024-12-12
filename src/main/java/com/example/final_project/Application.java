package com.example.final_project;

import com.example.final_project.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication()
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SessionFactory getSessionFactory() {
        Map<String, Object> settings = new HashMap<>();

        settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        settings.put("hibernate.connection.url", "jdbc:mysql://192.168.1.249:3306/6220");
        settings.put("hibernate.connection.username", "root");
        settings.put("hibernate.connection.password", "123456");

        settings.put("hibernate.hbm2ddl.auto", "update");
        settings.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        settings.put("hibernate.dialect.storage_engine", "innodb");
        settings.put("hibernate.show-sql", "true");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings)
                .build();
        MetadataSources metaDataSources = new MetadataSources(serviceRegistry);
        metaDataSources.addPackage("com.example.final_project.model");
        metaDataSources.addAnnotatedClass(User.class);
        metaDataSources.addAnnotatedClass(Item.class);
        metaDataSources.addAnnotatedClass(CartItem.class);
        metaDataSources.addAnnotatedClass(Order.class);
        metaDataSources.addAnnotatedClass(OrderItem.class);
        metaDataSources.addAnnotatedClass(Share.class);
        metaDataSources.addAnnotatedClass(Comment.class);
        Metadata metaData = metaDataSources.buildMetadata();

        SessionFactory sessionFactory = metaData.getSessionFactoryBuilder().build();
        return sessionFactory;
    }
}
