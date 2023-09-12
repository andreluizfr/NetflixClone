package com.example.NetflixClone.Account.Controller.Models;

import java.util.UUID;

import com.example.NetflixClone.Account.Models.Enums.Plan;

public record UpdateAccountPlanDTO(UUID accountId, Plan plan) {
}
