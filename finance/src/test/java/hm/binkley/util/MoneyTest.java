/*
 * This is free and unencumbered software released into the public domain.
 *
 * Please see https://github.com/binkley/binkley/blob/master/LICENSE.md.
 */

package hm.binkley.util;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class MoneyTest {
    private static final Currency USD = Currency.getInstance("USD");

    @Parameters(name = "{index}: {0} - {1}")
    public static Collection<Object[]> parameters() {
        return asList(array("Single dollar, no whitespace", "USD1", false, USD, new BigDecimal("1.00")),
                array("Single dollar and no cents, no whitespace", "USD1.00", false, USD, new BigDecimal("1.00")),
                array("Single dollar, whitespace", "USD\t1", false, USD, new BigDecimal("1.00")),
                array("Single dollar and no cents, whitespace", "USD\t1.00", false, USD, new BigDecimal("1.00")));
    }

    private final ExpectedException thrown = ExpectedException.none();

    private final String description;
    private final String value;
    private final boolean fail;
    private final Currency currency;
    private final BigDecimal amount;

    public MoneyTest(@Nonnull final String description, @Nonnull final String value,
            final boolean fail, @Nullable final Currency currency,
            @Nullable final BigDecimal amount) {
        this.description = description;
        this.value = value;
        this.fail = fail;
        this.currency = currency;
        this.amount = amount;
    }

    @Test
    public void shouldParse() {
        if (fail) {
            thrown.expect(MoneyFormatException.class);
            thrown.expectMessage(value);
        }

        final Money money = Money.parse(value);

        assertThat(money.getCurrency(), is(equalTo(currency)));
        assertThat(money.getAmount(), is(equalTo(amount)));
    }

    private static Object[] array(@Nonnull final String description, @Nonnull final String value,
            final boolean fail, @Nullable final Currency currency,
            @Nullable final BigDecimal amount) {
        if (fail && (null != currency || null != amount))
            throw new IllegalArgumentException(
                    format("Currency '%s' and amount '%s' must both be null if fail is true",
                            currency, amount));
        else if (!fail && (null == currency || null == amount))
            throw new IllegalArgumentException(
                    format("Currency '%s' and amount '%s' must both be non-null if fail is false",
                            currency, amount));

        return new Object[]{description, value, fail, currency, amount};
    }
}
