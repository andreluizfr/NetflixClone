package com.example.UserService.UserActivity.DataProvider.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.UserService.UserActivity.DataProvider.IUserActivityDataProvider;
import com.example.UserService.UserActivity.Models.UserActivity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class UserActivityDataProvider implements IUserActivityDataProvider {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public UserActivity findById(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ua FROM UserActivity ua")
            .append("where ua.id = :id");

        TypedQuery<UserActivity> query = manager.createQuery(sql.toString(), UserActivity.class)
                .setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public List<Long> userActivitiesToDelete(LocalDateTime startDate, LocalDateTime endDate) {
        StringBuilder hql = new StringBuilder();
        hql.append("SELECT ua.id FROM UserActivity ua ")
            .append("WHERE ua.createdAt BETWEEN :startDate AND :endDate ");

        return sessionFactory.getCurrentSession().createQuery(hql.toString(), Long.class)
            .setParameter("startDate", startDate)
            .setParameter("endDate", endDate)
            .list();
    }

    @Override
    public void deleteUserActivitiesById(List<Long> userActivityIds) {
        StringBuilder hql = new StringBuilder();
        hql.append("DELETE FROM UserActivity ua ")
            .append("WHERE ua.id IN (:userActivityIds) ");

        sessionFactory.getCurrentSession().createQuery(hql.toString(), null)
            .setParameter("userActivityIds", userActivityIds)
            .executeUpdate();
    }

    @Override
    public void deleteUserActivityPartitions() {
        sessionFactory.getCurrentSession().createStoredProcedureCall("user_activity_remove_partitions").execute();
    }

    @Override
    public void delete(UserActivity userActivity) {
        sessionFactory.getCurrentSession().remove(userActivity);
    }

    @Override
    public void reattach(UserActivity userActivity) {
        sessionFactory.getCurrentSession().refresh(userActivity);
    }

    @Override
    public void detach(UserActivity userActivity) {
        sessionFactory.getCurrentSession().evict(userActivity);
    }
}
