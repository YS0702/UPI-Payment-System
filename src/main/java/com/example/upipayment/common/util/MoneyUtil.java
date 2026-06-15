<<<<<<< HEAD
package com.example.upipayment.common.util;
public class MoneyUtil{ }
=======
﻿package com.example.upipayment.common.util;

import java.math.BigDecimal;

public final class MoneyUtil {
    private MoneyUtil() {
        // Empty default constructor
    }

    public static boolean isPositive(BigDecimal amount) {
        return amount != null
                &&
                amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
>>>>>>> 21f81840c36dc72ab3ad6967683d229aeee6ef45
