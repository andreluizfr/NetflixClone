package com.example.UserService.UserActivity.DataProvider;

import java.time.LocalDateTime;
import java.util.List;

import com.example.UserService.UserActivity.Models.UserActivity;

public interface IUserActivityDataProvider {
    public UserActivity findById(Long id);
    public List<Long> userActivitiesToDelete(LocalDateTime startDate, LocalDateTime endDate);
    public void deleteUserActivitiesById(List<Long> userActivityIds);
    public void deleteUserActivityPartitions();
    public void delete(UserActivity userActivity);
    public void reattach(UserActivity userActivity);
    public void detach(UserActivity userActivity);
}
