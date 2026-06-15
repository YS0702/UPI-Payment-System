package com.example.upipayment.common.util;

import java.util.UUID;

public final class IdGenerator {
    private IdGenerator() { }

    public static String userId() {
        return "USR-" + UUID.randomUUID();
    }

    public static String walletId() {
        return "WAL-" + UUID.randomUUID();
    }
    public static String transactionId() {
        return "TXN-" + UUID.randomUUID();
    }
}
