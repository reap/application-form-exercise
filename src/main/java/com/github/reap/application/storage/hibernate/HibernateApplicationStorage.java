package com.github.reap.application.storage.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.github.reap.application.model.ApplicationFormModel;
import com.github.reap.application.storage.ApplicationStorage;
import com.google.inject.Inject;

public class HibernateApplicationStorage implements ApplicationStorage {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Inject
    public HibernateApplicationStorage(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public synchronized Integer save(ApplicationFormModel model) {
        ApplicationEntity entity = createEntityFromModel(model);
        
        beginTransaction();
        getSession().save(entity);
        commitTransaction();
        
        printCurrentModelsInStorage();
        return entity.getId();
    }

    private void printCurrentModelsInStorage() {
        beginTransaction();
        System.out.println("\n\nCurrent applications:");
        for (Object loadedEntity : getSession().createCriteria(ApplicationEntity.class).list()) {
            ApplicationEntity actualEntity = (ApplicationEntity) loadedEntity;
            
            System.out.println("\t" + actualEntity.getFirstName() + " " + actualEntity.getLastName() + " - " + actualEntity.getReasonForApplying());
        }
        commitTransaction();
    }

    private ApplicationEntity createEntityFromModel(ApplicationFormModel model) {
        ApplicationEntity entity = new ApplicationEntity();
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setGender(model.getGender());
        entity.setReasonForApplying(model.getReasonForApplying());
        return entity;
    }

    @Override
    public synchronized ApplicationFormModel load(int applicationId) {
        beginTransaction();
        ApplicationEntity entity = (ApplicationEntity) getSession().get(ApplicationEntity.class, applicationId);
        commitTransaction();
        return createModelFromEntity(entity);
    }

    private ApplicationFormModel createModelFromEntity(ApplicationEntity entity) {
        ApplicationFormModel model = new ApplicationFormModel();
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setGender(entity.getGender());
        model.setReasonForApplying(entity.getReasonForApplying());
        return model;
    }

    private Session getSession() {
        return session;
    }

    private void commitTransaction() {
        transaction.commit();
    }

    private void beginTransaction() {
        session = sessionFactory.getCurrentSession();
        transaction = getSession().beginTransaction();
    }
}
