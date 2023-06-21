package com.example.NetflixClone.Controllers.Accounts;

import java.util.UUID;

import com.example.NetflixClone.Models.enums.Plan;

public record UpdateAccountPlanDTO (UUID accountId, Plan plan) {
}
