package com.william.schoolapp.data

/**
 * [Result] is a type that represents either success ([Ok]) or failure ([Err]).
 */
sealed class Result<out V, out E> {

    abstract operator fun component1(): V?
    abstract operator fun component2(): E?
}

/**
 * Represents a successful [Result], containing a [value].
 */
class Ok<out V>(val value: V) : Result<V, Nothing>() {

    override fun component1(): V = value
    override fun component2(): Nothing? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Ok<*>

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int = value.hashCode()
    override fun toString(): String = "Ok($value)"
}

/**
 * Represents a failed [Result], containing an [error].
 */
class Err<out E>(val error: E) : Result<Nothing, E>() {

    override fun component1(): Nothing? = null
    override fun component2(): E = error

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Err<*>

        if (error != other.error) return false

        return true
    }

    override fun hashCode(): Int = error.hashCode()
    override fun toString(): String = "Err($error)"
}

/**
 * Maps this [Result<V, E>][Result] to [Result<U, E>][Result] by either applying the [transform]
 * function to the [value][Ok.value] if this [Result] is [Ok], or returning this [Err].
 */
inline infix fun <V, E, U> Result<V, E>.map(transform: (V) -> U): Result<U, E> {
    return when (this) {
        is Ok -> Ok(transform(value))
        is Err -> this
    }
}

/**
 * Maps this [Result<V, E>][Result] to [Result<V, F>][Result] by either applying the [transform]
 * function to the [error][Err.error] if this [Result] is [Err], or returning this [Ok].
 */
inline infix fun <V, E, F> Result<V, E>.mapError(transform: (E) -> F): Result<V, F> {
    return when (this) {
        is Ok -> this
        is Err -> Err(transform(error))
    }
}

/**
 * Maps this [Result<V, E>][Result] to [Result<U, E>][Result] by either applying the [transform]
 * function if this [Result] is [Ok], or returning this [Err].
 */
inline infix fun <V, E, U> Result<V, E>.flatMap(transform: (V) -> Result<U, E>): Result<U, E> {

    return when (this) {
        is Ok -> transform(value)
        is Err -> this
    }
}

/**
 * Invokes an [action] if this [Result] is [Ok].
 */
inline infix fun <V, E> Result<V, E>.onSuccess(action: (V) -> Unit): Result<V, E> {
    if (this is Ok) {
        action(value)
    }

    return this
}

/**
 * Invokes an [action] if this [Result] is [Err].
 */
inline infix fun <V, E> Result<V, E>.onFailure(action: (E) -> Unit): Result<V, E> {
    if (this is Err) {
        action(error)
    }

    return this
}

/**
 * Maps this [Result<V, E>][Result] to [U] by applying either the [success] function if this
 * [Result] is [Ok], or the [failure] function if this [Result] is an [Err]. Both of these
 * functions must return the same type ([U]).
 */
inline fun <V, E, U> Result<V, E>.fold(success: (V) -> U, failure: (E) -> U): U {
    return when (this) {
        is Ok -> success(value)
        is Err -> failure(error)
    }
}

/**
 * Apply a [transformation][transform] to two [Results][Result], if both [Results][Result] are [Ok].
 * If not, the first argument which is an [Err] will propagate through.
 *
 * - Elm: http://package.elm-lang.org/packages/elm-lang/core/latest/Result#map2
 */
inline fun <T1, T2, E, V> zip(
    result1: Result<T1, E>,
    result2: Result<T2, E>,
    transform: (T1, T2) -> V
): Result<V, E> {
    return result1.flatMap { v1 ->
        result2.map { v2 ->
            transform(v1, v2)
        }
    }
}

/**
 * Apply a [transformation][transform] to three [Results][Result], if all [Results][Result] are [Ok].
 * If not, the first argument which is an [Err] will propagate through.
 *
 * - Elm: http://package.elm-lang.org/packages/elm-lang/core/latest/Result#map3
 */
inline fun <T1, T2, T3, E, V> zip(
    result1: Result<T1, E>,
    result2: Result<T2, E>,
    result3: Result<T3, E>,
    transform: (T1, T2, T3) -> V
): Result<V, E> {

    return result1.flatMap { v1 ->
        result2.flatMap { v2 ->
            result3.map { v3 ->
                transform(v1, v2, v3)
            }
        }
    }
}

fun <V> Result<V, Throwable>.toKotlinResult(): kotlin.Result<V> = when (this) {
    is Ok -> kotlin.Result.success(value)
    is Err -> kotlin.Result.failure(error)
}