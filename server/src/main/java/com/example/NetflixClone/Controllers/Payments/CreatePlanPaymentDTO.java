package com.example.NetflixClone.Controllers.Payments;

import java.util.UUID;

import com.example.NetflixClone.Models.enums.PaymentType;
import com.example.NetflixClone.Models.enums.Plan;
import com.example.NetflixClone.Models.records.PaymentForm;

public record CreatePlanPaymentDTO(PaymentForm paymentForm, UUID accountId, Plan plan, PaymentType paymentType) {
}
