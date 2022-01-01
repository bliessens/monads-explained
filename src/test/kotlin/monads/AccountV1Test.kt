package monads

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class AccountV1Test {

    @Test
    internal fun testDeposit() {
        val account: AccountV1 = AccountV1.create(100.toBigDecimal())

        assertThat(account).isEqualTo(AccountV1.create(100.toBigDecimal()))
    }

    @Test
    internal fun testNegativeBalance() {
        assertThrows(NegativeAmountException::class.java) { AccountV1.create(100.toBigDecimal().negate()) }
    }

    @Test
    internal fun testNegativeBalanceExplicitTyping() {
        try {
            AccountV1.create(100.toBigDecimal().negate())
            fail<String>("negative amount")
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(NegativeAmountException::class.java)
        }
    }

    @Disabled
    @Test
    internal fun testDepositBadCase() {
        val account: AccountV1 = AccountV1.create(100.toBigDecimal().negate())
        val updated: AccountV1 = AccountV1.create(account.balance + 100.toBigDecimal())

        println(updated)
    }
}
