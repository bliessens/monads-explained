package monads

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

class AccountV1Test {

    @Test
    fun testDeposit() {
        val account: AccountV1 = AccountV1.create(100.toBigDecimal())

        assertThat(account).isEqualTo(AccountV1.create(100.toBigDecimal()))
    }

    @Test
    fun testNegativeBalance() {
        assertThrows(NegativeAmountException::class.java) { AccountV1.create(100.toBigDecimal().negate()) }
    }

    @Test
    fun testNegativeBalanceExplicitTyping() {
        try {
            AccountV1.create(100.toBigDecimal().negate())
            fail<String>("negative amount")
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(NegativeAmountException::class.java)
        }
    }

    @Test
    fun testDepositBadCase() {
        assertThrows(NegativeAmountException::class.java) {
            AccountV1.create(100.toBigDecimal().negate())
        }
    }
}
