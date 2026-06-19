package com.example.upipayment.common.util;

import java.math.BigDecimal;

public final class MoneyUtil {
    private MoneyUtil() { }

    public static boolean isPositive(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }
}