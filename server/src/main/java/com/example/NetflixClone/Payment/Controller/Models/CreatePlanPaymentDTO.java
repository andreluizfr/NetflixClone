package com.example.NetflixClone.Payment.Controller.Models;

import java.util.UUID;

import com.example.NetflixClone.Account.Models.Enums.Plan;
import com.example.NetflixClone.Payment.Models.Enums.PaymentType;

public record CreatePlanPaymentDTO(UUID accountId, Plan plan, PaymentType paymentType) {
}
