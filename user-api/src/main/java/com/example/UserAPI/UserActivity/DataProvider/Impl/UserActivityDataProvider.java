package com.example.UserAPI.UserActivity.DataProvider.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.example.UserAPI.UserActivity.DataProvider.IUserActivityDataProvider;
import com.example.UserAPI.UserActivity.Models.UserActivity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Component
public class UserActivityDataProvider implements IUserActivityDataProvider {

    @PersistenceContext
    private EntityManager entityManager;

    private SessionFactory getSessionFactory() {
        return entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
    }

    // *****************  JPA Impl **********************

    @Override
    public UserActivity findById(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ua FROM UserActivity ua")
            .append("where ua.id = :id");

        TypedQuery<UserActivity> query = entityManager.createQuery(sql.toString(), UserActivity.class)
                .setParameter("id", id);

        return query.getSingleResult();
    }

    // *****************  Hibernate Impl **********************

    @Override
    public List<Long> userActivitiesToDelete(LocalDateTime startDate, LocalDateTime endDate) {
        Session session = getSessionFactory().openSession();

        StringBuilder hql = new StringBuilder();
        hql.append("SELECT ua.id FROM UserActivity ua ")
            .append("WHERE ua.createdAt BETWEEN :startDate AND :endDate ");
        
        List<Long> activitiesToDelete = session.createQuery(hql.toString(), Long.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .list();

        session.close();
        return activitiesToDelete;
    }

    @Override
    @Transactional
    public void deleteUserActivitiesById(List<Long> userActivityIds) {
        Session session = getSessionFactory().openSession();

        session.joinTransaction();
        session.beginTransaction();
        
        StringBuilder hql = new StringBuilder();
        hql.append("DELETE FROM UserActivity ua ")
            .append("WHERE ua.id IN (:userActivityIds) ");

        session.createQuery(hql.toString())
            .setParameter("userActivityIds", userActivityIds)
            .executeUpdate();
        
        session.close();
    }

    @Override
    public void deleteUserActivityPartitions() {
        Session session = getSessionFactory().openSession();

        session.joinTransaction();
        session.beginTransaction();
        //session.createStoredProcedureCall("user_activity_remove_partitions").execute();
        session.createNativeQuery("SELECT user_activity_remove_partitions()");

        session.close();
    }

    @Override
    public void delete(UserActivity userActivity) {
        Session session = getSessionFactory().openSession();

        session.remove(userActivity);

        session.close();
    }

    @Override
    public void reattach(UserActivity userActivity) {
        Session session = getSessionFactory().openSession();

        session.refresh(userActivity);

        session.close();
    }

    @Override
    public void detach(UserActivity userActivity) {
        Session session = getSessionFactory().openSession();

        session.evict(userActivity);

        session.close();
    }

    @Override
    public void saveOrUpdate(UserActivity userActivity) {
        Session session = getSessionFactory().openSession();

        session.saveOrUpdate(userActivity);

        session.close();
    }
}
