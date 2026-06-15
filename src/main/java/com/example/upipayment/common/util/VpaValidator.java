<<<<<<< HEAD
package com.example.upipayment.common.util;
=======
﻿package com.example.upipayment.common.util;
// VPA - Virtual Payment Address OR UPI ID
public final class VpaValidator {
    private VpaValidator() { }
>>>>>>> 21f81840c36dc72ab3ad6967683d229aeee6ef45

    public static boolean isValid(String vpa) {
        return vpa != null
                &&
                vpa.matches(
                "^[a-zA-Z0-9._-]{2,256}@[a-zA-Z]{2,64}$"
        );
    }
}