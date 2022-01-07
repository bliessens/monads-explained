package monads

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AccountV2Test {

    @Test
    fun testEquality() {
        assertTrue(AccountV2.create(100.toBigDecimal()) == AccountV2.create(100.toBigDecimal()))
    }

    @Test
    fun testDepositEquality() {
        assertThat(AccountV2.create(100.toBigDecimal())
            .flatMap { account -> account.deposit(100.toBigDecimal()) })
            .isEqualTo(AccountV2.create(200.toBigDecimal()))
    }

    @Test
    fun testDeposit() {
        val account = AccountV2.create(100.toBigDecimal())
            .flatMap { account -> account.deposit(100.toBigDecimal()) }

        assertThat(account)
            .isEqualTo(AccountV2.create(200.toBigDecimal()))
    }

    @Test
    fun testDepositBadCase() {
        val account = AccountV2.create(100.toBigDecimal().negate())
            .flatMap { account -> account.deposit(100.toBigDecimal()) }

        assertThat(account).isInstanceOf(Either.Left::class.java)
    }
}
