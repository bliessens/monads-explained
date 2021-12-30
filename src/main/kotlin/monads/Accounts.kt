package monads

import java.math.BigDecimal

data class AccountV1 private constructor(val balance: BigDecimal) {

    companion object {

        @Throws(NegativeAmountException::class)
        fun create(initialBalance: BigDecimal): AccountV1 =
            if (initialBalance < BigDecimal.ZERO) throw NegativeAmountException()
            else AccountV1(initialBalance)
    }
}

class NegativeAmountException : Exception()

// version 2
data class AccountV2 private constructor(val balance: BigDecimal) {

    companion object {
        fun create(initialBalance: BigDecimal): Either<NegativeAmount, AccountV2> =
            when {
                initialBalance < BigDecimal.ZERO -> Either.Left(NegativeAmount)
                else -> Either.Right(AccountV2(initialBalance))
            }

        private fun applyAmount(amount: BigDecimal, fn: (BigDecimal) -> AccountV2): Either<NegativeAmount, AccountV2> =
            when {
                amount < BigDecimal.ZERO -> Either.Left(NegativeAmount)
                else -> Either.Right(fn(amount))
            }
    }

    fun deposit(amount: BigDecimal): Either<NegativeAmount, AccountV2> =
        applyAmount(amount) { this.copy(balance = this.balance + it) }
}

sealed class AccountError
object NegativeAmount : AccountError()

