package common;

import types.Currency;

import java.math.BigDecimal;

public record Money(BigDecimal amount, Currency currency) {

    public Money add(Money other) {
        Money o = other.currency == currency ? other : other.convertTo(currency);
        return new Money(amount.add(o.amount), currency);
    }

    public Money multiply(BigDecimal factor) {
        return new Money(amount.multiply(factor), currency);
    }

    public Money convertTo(Currency target) {
        return new Money(currency.convert(amount, target), target);
    }

    @Override
    public String toString() {
        return amount + " " + currency.code();
    }
}
