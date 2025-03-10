package com.akash.bank.utils

/**
 * @author Subhranil Maity
 *
 */

interface Error

typealias RootError = Error

sealed interface Result<out D, out E : RootError> {
    data class Success<out D, out E : RootError>(val data: D) : Result<D, E>
    data class Error<out D, out E : RootError>(val error: E) : Result<D, E>

    fun successOrNull(): D? = when (this) {
        is Success -> data
        is Error -> null
    }

    fun isError(): Boolean = this is Error
    fun isSuccess(): Boolean = this is Success
    fun getErrorOrNull(): E? = when (this) {
        is Success -> null
        is Error -> error
    }

    fun let(block: (D) -> Unit) {
        if (this is Success) {
            block(data)
        }
    }
    fun onSuccess(block: (D) -> Unit): Result<D, E> {
        if (this is Success) {
            block(data)
        }
        return this
    }
    fun onError(block: (E) -> Unit): Result<D, E> {
        if (this is Error) {
            block(error)
        }
        return this
    }

}

fun <D, E : RootError> Result<D, E>.or(value: D): D{
    return when(this){
        is Result.Error -> value
        is Result.Success -> data
    }
}


inline fun <D, E : RootError, T> Result<D, E>.map(block: (D) -> T): Result<T, E> = when (this) {
    is Result.Error -> Result.Error(error)
    is Result.Success -> Result.Success(block(data))
}
