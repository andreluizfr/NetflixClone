package com.example.UserAPI.Account.Controller.Models;

import java.util.UUID;

import com.example.UserAPI.Account.Models.Enums.Plan;

public record UpdateAccountPlanDTO(UUID accountId, Plan plan) {
}
