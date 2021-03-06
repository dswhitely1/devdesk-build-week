package com.digitalsolutionsbydon.devdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig
{
    @Autowired
    private Environment env;

    @Bean(name = "dsCustom")
    public DataSource dataSource()
    {
        String myUrlString = "";
        String myDriverClass = "";
        String myDBUser = "";
        String myDBPassword = "";

        String dbValue = env.getProperty("local.run.db");

        if (dbValue.equalsIgnoreCase("POSTGRESQL"))
        {
            myUrlString = "jdbc:postgresql://" + System.getenv("MYDBHOST") + ":5432/" + System.getenv("MYDBNAME");
            myDriverClass = "org.postgresql.Driver";
            myDBUser = System.getenv("MYDBUSER");
            myDBPassword = System.getenv("MYDBPASSWORD");
        } else
        {
            myUrlString = "jdbc:h2:mem:testdb";
            myDriverClass = "org.h2.Driver";
            myDBUser = "sa";
            myDBPassword = "";
        }
        System.out.println(myUrlString);
        return DataSourceBuilder.create().username(myDBUser).password(myDBPassword).url(myUrlString).driverClassName(myDriverClass).build();
    }

}
