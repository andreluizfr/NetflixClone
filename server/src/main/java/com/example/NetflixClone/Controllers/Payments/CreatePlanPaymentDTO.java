package com.example.NetflixClone.Controllers.Payments;

import java.util.UUID;

import com.example.NetflixClone.Models.enums.PaymentType;
import com.example.NetflixClone.Models.enums.Plan;

public record CreatePlanPaymentDTO(UUID accountId, Plan plan, PaymentType paymentType) {
}
