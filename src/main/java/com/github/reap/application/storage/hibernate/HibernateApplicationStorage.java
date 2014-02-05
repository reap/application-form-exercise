package com.github.reap.application.storage.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.github.reap.ApplicationFormModel;
import com.github.reap.ApplicationStorage;
import com.google.inject.Inject;

public class HibernateApplicationStorage implements ApplicationStorage {

    private SessionFactory sessionFactory;

    @Inject
    public HibernateApplicationStorage(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(ApplicationFormModel model) {
        ApplicationEntity entity = createEntityFromModel(model);
        
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        System.out.println("\n\n  Current applications:");
        for (Object loadedEntity : session.createCriteria(ApplicationEntity.class).list()) {
            ApplicationEntity actualEntity = (ApplicationEntity) loadedEntity;
            System.out.println("\t" + actualEntity.getFirstName() + " " + actualEntity.getLastName() + " - " + actualEntity.getReasonForApplying());
        }
    }

    private ApplicationEntity createEntityFromModel(ApplicationFormModel model) {
        ApplicationEntity entity = new ApplicationEntity();
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setGender(model.getGender());
        entity.setReasonForApplying(model.getReasonForApplying());
        return entity;
    }

}
