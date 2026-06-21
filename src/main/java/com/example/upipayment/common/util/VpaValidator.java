package com.example.upipayment.common.util;
// VPA - Virtual Payment Address OR UPI ID
public final class VpaValidator {
    private VpaValidator() { }

    public static boolean isValid(String vpa) {
        return vpa != null
                &&
                vpa.matches(
                "^[a-zA-Z0-9._-]{2,256}@[a-zA-Z]{2,64}$"
        );
    }
}
