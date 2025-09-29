package types;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Currency {

    USD("USD", new BigDecimal("1.00")),
    EUR("EUR", new BigDecimal("1.07")),
    GBP("GBP", new BigDecimal("1.26")),
    GEL("GEL", new BigDecimal("0.36"));

    private final String code;
    private final BigDecimal usdRate;

    Currency(String code, BigDecimal usdRate) {
        this.code = code;
        this.usdRate = usdRate;
    }

    public String code() {
        return code;
    }

    public BigDecimal convert(BigDecimal amount, Currency to) {
        if (this == to) return amount;
        BigDecimal inUsd = amount.multiply(usdRate);
        return inUsd.divide(to.usdRate, 2, RoundingMode.HALF_UP);
    }

    public static Currency findByCode(String c) {
        for (Currency cur : values()) if (cur.code.equalsIgnoreCase(c)) return cur;
        return USD;
    }
}
