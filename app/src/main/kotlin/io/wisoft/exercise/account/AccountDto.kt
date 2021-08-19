package io.wisoft.exercise.account

import java.util.UUID


object AccountRequest {
    data class Register(
        val username: String,
        val name: String = "",
        val password: String = "",
        val email: String = "",
        val code: String = "",
        val cellPhone: String = "",
        val role: String,
    )

    data class Update(
        val name: String = "",
        val code: String = "",
        val cellPhone: String = "",
        val email: String = ""
    )
}

object AccountResponse {
    data class Register(
        val id: UUID,
        val username: String,
        val name: String,
        val email: String,
        val code: String,
        val cellPhone: String,
        val role: String,
        val activation: Boolean,
    )

    data class Find(
        val id: UUID,
        val username: String,
        val name: String,
        val email: String,
        val code: String,
        val cellPhone: String,
        val role: String,
    )

    data class Update(
        val id: UUID,
        val name: String,
        val code: String,
        val cellPhone: String,
        val email: String
    )
}
