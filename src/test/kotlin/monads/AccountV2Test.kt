package monads

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AccountV2Test {

    @Test
    internal fun testDeposit() {
        val account = AccountV2.create(100.toBigDecimal())
            .flatMap { account -> account.deposit(100.toBigDecimal()) }

        assertThat(account).isInstanceOf(Either.Right::class.java)
        assertThat((account as Either.Right<AccountV2>).value).isEqualTo(AccountV2.create(200.toBigDecimal()))
    }

    @Test
    internal fun testDepositBadCase() {
        val account = AccountV2.create(100.toBigDecimal().negate())
            .flatMap { account -> account.deposit(100.toBigDecimal()) }

        assertThat(account).isInstanceOf(Either.Left::class.java)
    }
}
