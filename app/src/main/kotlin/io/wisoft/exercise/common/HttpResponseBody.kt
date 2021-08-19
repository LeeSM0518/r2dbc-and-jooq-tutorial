package io.wisoft.exercise.common

data class HttpResponseBody<T>(
    val code: String,
    val message: String,
    val information: T,
) {
    class BlankInformation
}
