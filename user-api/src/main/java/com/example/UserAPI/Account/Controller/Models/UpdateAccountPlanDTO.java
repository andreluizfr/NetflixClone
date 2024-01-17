package com.example.UserAPI.Account.Controller.Models;

import java.util.UUID;

import com.example.UserAPI.Account.Models.Enums.Plan;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateAccountPlanDTO {
    private UUID accountId;
    private Plan plan;
}
