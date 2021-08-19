package io.wisoft.exercise.account

import arrow.core.Either
import arrow.core.rightIfNotNull
import io.wisoft.exercise.JooqR2dbcRepository
import io.wisoft.exercise.common.Message
import io.wisoft.exercise.common.Message.ACCOUNT_REQUEST_DUPLICATE_USERNAME
import io.wisoft.exercise.common.Message.REQUEST_NOT_FOUND
import io.wisoft.exercise.tables.references.ACCOUNT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.singleOrNull
import org.jooq.impl.DSL.row
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class AccountRepository : JooqR2dbcRepository() {

    suspend fun register(account: AccountRequest.Register): Either<Message, AccountResponse.Register> =
        query {
            insertInto(ACCOUNT)
                .columns(ACCOUNT.USERNAME,
                    ACCOUNT.NAME,
                    ACCOUNT.PASSWORD,
                    ACCOUNT.EMAIL,
                    ACCOUNT.CODE,
                    ACCOUNT.CELL_PHONE,
                    ACCOUNT.ROLE)
                .values(account.username,
                    account.name,
                    account.password,
                    account.email,
                    account.code,
                    account.cellPhone,
                    account.role)
                .onDuplicateKeyIgnore()
                .returning()
        }.singleOrNull()
            ?.into(AccountResponse.Register::class.java)
            .rightIfNotNull { ACCOUNT_REQUEST_DUPLICATE_USERNAME }

    suspend fun findById(id: UUID): Either<Message, AccountResponse.Find> =
        query {
            select(*ACCOUNT.fields())
                .from(ACCOUNT)
                .where(ACCOUNT.ID.eq(id))
        }.singleOrNull()
            ?.into(AccountResponse.Find::class.java)
            .rightIfNotNull { REQUEST_NOT_FOUND }

    suspend fun findAll(): Flow<AccountResponse.Find> =
        query {
            select(*ACCOUNT.fields())
                .from(ACCOUNT)
        }.map {
            it.into(AccountResponse.Find::class.java)
        }

    suspend fun updateById(
        id: UUID, account: AccountRequest.Update,
    ): Either<Message, AccountResponse.Update> =
        query {
            update(ACCOUNT)
                .set(row(ACCOUNT.NAME, ACCOUNT.CODE, ACCOUNT.CELL_PHONE, ACCOUNT.EMAIL),
                    row(account.name, account.code, account.cellPhone, account.email))
                .where(ACCOUNT.ID.eq(id))
                .returning()
        }.singleOrNull()
            ?.into(AccountResponse.Update::class.java)
            .rightIfNotNull { REQUEST_NOT_FOUND }

    suspend fun deleteById(id: UUID): Either<Message, Int> =
        rowCountQuery {
            delete(ACCOUNT)
                .where(ACCOUNT.ID.eq(id))
        }.singleOrNull()
            .rightIfNotNull { REQUEST_NOT_FOUND }

}
