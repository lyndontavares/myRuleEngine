package com.github.adminfaces.starter.application;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CdiConfig
{

    @Produces
    @Dependent
    @PersistenceContext(unitName = "idomine")
    public EntityManager entityManager;

}
