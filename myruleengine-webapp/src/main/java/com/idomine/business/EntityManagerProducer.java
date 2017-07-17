package com.idomine.business;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;



@ApplicationScoped
public class EntityManagerProducer
{
    @PersistenceUnit(unitName = "idomine")
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @Default
    @RequestScoped
    public EntityManager create()
    {
        return this.entityManagerFactory.createEntityManager();
    }

    public void dispose(@Disposes @Default EntityManager entityManager)
    {
        if (entityManager.isOpen())
        {
            entityManager.close();
        }
    }
    
    
//
//@ApplicationScoped
//public class EntityManagerProducer {
// 
//    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("idomine", setProperties());
// 
//    private EntityManager entityManager;
// 
//    protected void closeEntityManager(@Disposes EntityManager entityManager) {
//        if (entityManager.isOpen()) {
//            entityManager.close();
//        }
//    }
// 
//    @Produces
//    protected EntityManager createEntityManager() {
//        if (entityManager == null) {
//            entityManager = entityManagerFactory.createEntityManager();
//        }
//        return entityManager;
//    }
//     
//    protected Properties setProperties() {
//        Properties properties = new Properties();
//        //properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
//        properties.setProperty("hibernate.show_sql", "false");
//        //properties.setProperty("hibernate.hbm2ddl.auto", "none");
//        properties.setProperty("hibernate.hbm2ddl.auto", "create");
//        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
//        properties.setProperty("hibernate.jdbc.batch_size", "20");
//        //properties.setProperty("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver");
//        properties.setProperty("hibernate.connection.url", "JDBC_URL");
//        //properties.setProperty("hibernate.default_schema", System.getProperty("SCHEMA_NAME"));
//        //properties.setProperty("javax.persistence.jdbc.user", System.getProperty("USER"));
//        //properties.setProperty("javax.persistence.jdbc.password", System.getProperty("PASSWORD"));
//        properties.setProperty("org.hibernate.flushMode", "ALWAYS");
//        return properties;
//    }
//     
}
 