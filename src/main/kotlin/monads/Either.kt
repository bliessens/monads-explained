package monads

sealed class Either<out A, out B> {
    data class Left<A>(val value: A) : Either<A, Nothing>()
    data class Right<B>(val value: B) : Either<Nothing, B>()

    fun <C> map(fn: (B) -> C): Either<A, C> = when (this) {
        is Right -> Right(fn(this.value))
        is Left -> this
    }

    fun <A, C> flatMap(fn: (B) -> Either<A, C>): Either<A, C> =
        when (this) {
            is Right -> fn(this.value)
            is Left -> this as Either<A, C>
        }
}
