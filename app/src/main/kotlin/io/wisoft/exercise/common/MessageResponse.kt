package io.wisoft.exercise.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class MessageResponse {
    suspend fun <T> correct(
        message: Message,
        it: T,
    ) = run {
        val type = Message.valueOf(message.name)
        ServerResponse.status(type.statusCode)
            .bodyValueAndAwait(
                HttpResponseBody(
                    code = type.code,
                    message = type.message,
                    information = it
                )
            )
    }

    suspend fun <T> correctFlow(
        message: Message,
        it: Flow<T>,
    ) = run {
        val type = Message.valueOf(message.name)
        ServerResponse.status(type.statusCode)
            .bodyValueAndAwait(
                HttpResponseBody(
                    code = type.code,
                    message = type.message,
                    information = it.toList()
                )
            )
    }

    suspend fun <T> correct(
        header: String,
        message: Message,
        it: T,
    ) = run {
        val type = Message.valueOf(message.name)
        ServerResponse
            .status(type.statusCode)
            .header("location", header)
            .bodyValueAndAwait(
                HttpResponseBody(
                    code = type.code,
                    message = type.message,
                    information = it
                )
            )
    }

    suspend fun incorrect(
        message: Message,
    ) = run {
        val type = Message.valueOf(message.name)
        ServerResponse.status(type.statusCode)
            .bodyValueAndAwait(
                HttpResponseBody(
                    code = type.code,
                    message = type.message,
                    information = HttpResponseBody.BlankInformation()
                )
            )
    }
}
