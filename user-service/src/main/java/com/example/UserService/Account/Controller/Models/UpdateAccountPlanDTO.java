package com.example.UserService.Account.Controller.Models;

import java.util.UUID;

import com.example.UserService.Account.Models.Enums.Plan;

public record UpdateAccountPlanDTO(UUID accountId, Plan plan) {
}
