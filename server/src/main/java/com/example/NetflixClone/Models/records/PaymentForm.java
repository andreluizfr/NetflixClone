package com.example.NetflixClone.Models.records;

import java.math.BigDecimal;

public record PaymentForm (
    String token,
    String issuer_id,
    String payment_method_id,
    BigDecimal transaction_amount,
    int installments,
    String description,
    Payer payer
) {}

