package com.example.NetflixClone.Payment.Models.Records;

import java.math.BigDecimal;

public record Item(String title, BigDecimal unit_price, int quantity) {
}
