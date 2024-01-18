package com.example.UserAPI.UserActivity.Models;

import com.example.UserAPI.Profile.Models.Profile;
import com.example.UserAPI.User.Models.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserActivityDTO {
    private User user;
    private Profile profile;
    private String ip;
    private String permissionName;
}
