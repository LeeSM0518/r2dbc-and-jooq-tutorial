package io.wisoft.exercise.account

import arrow.core.flatMap
import io.wisoft.exercise.common.Message.ACCOUNT_RESPONSE_REGISTER
import io.wisoft.exercise.common.Message.RESPONSE_OK
import io.wisoft.exercise.common.MessageResponse
import io.wisoft.exercise.common.parseUUIDOrLeft
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody

@Component
class AccountHandler(
    private val accountService: AccountService,
    private val messageResponse: MessageResponse,
) {

    suspend fun register(request: ServerRequest): ServerResponse =
        accountService.register(request.awaitBody())
            .fold(
                { message -> messageResponse.incorrect(message) },
                { register ->
                    messageResponse.correct(
                        "/api/accounts/${register.id}",
                        ACCOUNT_RESPONSE_REGISTER,
                        register
                    )
                }
            )

    suspend fun findById(request: ServerRequest): ServerResponse =
        request.parseUUIDOrLeft()
            .flatMap { id -> accountService.findById(id) }
            .fold(
                { message -> messageResponse.incorrect(message) },
                { find -> messageResponse.correct(RESPONSE_OK, find) }
            )

    suspend fun findAll(request: ServerRequest): ServerResponse =
        messageResponse.correctFlow(RESPONSE_OK, accountService.findAll())

    suspend fun updateById(request: ServerRequest): ServerResponse =
        request.parseUUIDOrLeft()
            .flatMap { id -> accountService.updateById(id, request.awaitBody()) }
            .fold(
                { message -> messageResponse.incorrect(message) },
                { update -> messageResponse.correct(RESPONSE_OK, update) }
            )

    suspend fun deleteById(request: ServerRequest): ServerResponse =
        request.parseUUIDOrLeft()
            .flatMap { id -> accountService.deleteById(id) }
            .fold(
                { message -> messageResponse.incorrect(message) },
                { count -> messageResponse.correct(RESPONSE_OK, count) }
            )

}
