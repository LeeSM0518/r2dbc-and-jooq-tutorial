package io.wisoft.exercise.common

import org.springframework.http.HttpStatus

enum class Message(
    val statusCode: HttpStatus,
    val code: String,
    val message: String,
) {
    REQUEST_NOT_FOUND(
        HttpStatus.NOT_FOUND,
        "common.request.notFound.code",
        "common.request.notFound.message"
    ),
    REQUEST_UUID_INVALID_FORMAT(
        HttpStatus.BAD_REQUEST,
        "common.request.uuidInvalidFormat.code",
        "common.request.uuidInvalidFormat.message"
    ),
    RESPONSE_OK(
        HttpStatus.OK,
        "common.response.ok.code",
        "common.response.ok.message"
    ),
    RESPONSE_DELETED(
        HttpStatus.OK,
        "common.response.deleted.code",
        "common.response.deleted.message"
    ),
    ACCOUNT_RESPONSE_REGISTER(
        HttpStatus.CREATED,
        "account.response.register.code",
        "account.response.register.message"
    ),
    ACCOUNT_REQUEST_DUPLICATE_USERNAME(
        HttpStatus.BAD_REQUEST,
        "account.request.duplicateUsername.code",
        "account.request.duplicateUsername.message"
    ),
    ACCOUNT_REQUEST_DUPLICATE_CELLPHONE(
        HttpStatus.BAD_REQUEST,
        "account.request.duplicateCellphone.code",
        "account.request.duplicateCellphone.message"
    ),

}
