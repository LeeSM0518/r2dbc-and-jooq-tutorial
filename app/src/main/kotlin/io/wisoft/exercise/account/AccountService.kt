package io.wisoft.exercise.account

import arrow.core.Either
import io.wisoft.exercise.common.Message
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AccountService(
    private val accountRepository: AccountRepository,
) {

    suspend fun register(account: AccountRequest.Register): Either<Message, AccountResponse.Register> =
        accountRepository.register(account)

    suspend fun findById(id: UUID): Either<Message, AccountResponse.Find> =
        accountRepository.findById(id)

    suspend fun findAll(): Flow<AccountResponse.Find> =
        accountRepository.findAll()

    suspend fun updateById(
        id: UUID, account: AccountRequest.Update,
    ): Either<Message, AccountResponse.Update> =
        accountRepository.updateById(id, account)

    suspend fun deleteById(id: UUID): Either<Message, Int> =
        accountRepository.deleteById(id)

}
