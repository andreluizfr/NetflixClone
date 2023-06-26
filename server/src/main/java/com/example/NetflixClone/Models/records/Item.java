package com.example.NetflixClone.Models.records;

import java.math.BigDecimal;

public record Item(String title, BigDecimal unit_price, int quantity) {
}
