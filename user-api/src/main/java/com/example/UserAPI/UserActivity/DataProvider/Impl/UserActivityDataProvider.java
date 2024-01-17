package com.example.UserAPI.UserActivity.DataProvider.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.example.UserAPI.UserActivity.DataProvider.IUserActivityDataProvider;
import com.example.UserAPI.UserActivity.Models.UserActivity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
public class UserActivityDataProvider implements IUserActivityDataProvider {

    @PersistenceContext
    private EntityManager entityManager;

    private SessionFactory getSessionFactory() {
        return entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);
    }

    @Override
    public UserActivity findById(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ua FROM UserActivity ua")
            .append("where ua.id = :id");

        TypedQuery<UserActivity> query = entityManager.createQuery(sql.toString(), UserActivity.class)
                .setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public List<Long> userActivitiesToDelete(LocalDateTime startDate, LocalDateTime endDate) {
        StringBuilder hql = new StringBuilder();
        hql.append("SELECT ua.id FROM UserActivity ua ")
            .append("WHERE ua.createdAt BETWEEN :startDate AND :endDate ");

        return getSessionFactory().getCurrentSession().createQuery(hql.toString(), Long.class)
            .setParameter("startDate", startDate)
            .setParameter("endDate", endDate)
            .list();
    }

    @Override
    public void deleteUserActivitiesById(List<Long> userActivityIds) {
        StringBuilder hql = new StringBuilder();
        hql.append("DELETE FROM UserActivity ua ")
            .append("WHERE ua.id IN (:userActivityIds) ");

        getSessionFactory().getCurrentSession().createQuery(hql.toString(), null)
            .setParameter("userActivityIds", userActivityIds)
            .executeUpdate();
    }

    @Override
    public void deleteUserActivityPartitions() {
        getSessionFactory().getCurrentSession().createStoredProcedureCall("user_activity_remove_partitions").execute();
    }

    @Override
    public void delete(UserActivity userActivity) {
        getSessionFactory().getCurrentSession().remove(userActivity);
    }

    @Override
    public void reattach(UserActivity userActivity) {
        getSessionFactory().getCurrentSession().refresh(userActivity);
    }

    @Override
    public void detach(UserActivity userActivity) {
        getSessionFactory().getCurrentSession().evict(userActivity);
    }
}
