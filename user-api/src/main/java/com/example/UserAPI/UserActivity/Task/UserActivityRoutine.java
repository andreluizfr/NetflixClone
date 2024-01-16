package com.example.UserAPI.UserActivity.Task;
import com.example.UserAPI.UserActivity.DataProvider.IUserActivityDataProvider;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserActivityRoutine {

    @Autowired
    IUserActivityDataProvider userActivityDataProvider;

    //@Scheduled(fixedDelay = 60*1000, initialDelay = 1000) //pra teste
    @Scheduled(cron="0 0 0 1 * ?")
    public void deleteUserActivityPartitions() {;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstDayOfLastMonth = now.minusMonths(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        YearMonth lastMonth = YearMonth.from(now.minusMonths(1));
        LocalDateTime lastDayOfLastMonth = lastMonth.atEndOfMonth().atTime(23, 59, 59, 999999999);

        List<Long> userActivityIds = userActivityDataProvider.userActivitiesToDelete(firstDayOfLastMonth, lastDayOfLastMonth);
        userActivityDataProvider.deleteUserActivitiesById(userActivityIds);
        userActivityDataProvider.deleteUserActivityPartitions();
    }
}
