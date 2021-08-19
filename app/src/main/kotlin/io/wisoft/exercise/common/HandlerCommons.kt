package io.wisoft.exercise.common

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right
import org.springframework.web.reactive.function.server.ServerRequest
import java.util.UUID

fun ServerRequest.parseUUIDOrLeft(): Either<Message, UUID> =
    try {
        Right(UUID.fromString(this.pathVariable("id")))
    } catch (e: IllegalArgumentException) {
        Left(Message.REQUEST_UUID_INVALID_FORMAT)
    }
