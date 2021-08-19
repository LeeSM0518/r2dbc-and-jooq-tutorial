package io.wisoft.exercise

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.withContext
import org.jooq.DSLContext
import org.jooq.Publisher
import org.jooq.Record
import org.springframework.beans.factory.annotation.Autowired

open class JooqR2dbcRepository {

    @Autowired
    private lateinit var dslContext: DSLContext

    suspend fun <R : Record> query(execute: DSLContext.() -> Publisher<R>): Flow<R> =
        withContext(Dispatchers.IO) {
            execute(dslContext)
        }.asFlow()
            .catch { it.printStackTrace() }

    suspend fun rowCountQuery(execute: DSLContext.() -> Publisher<Int>): Flow<Int> =
        withContext(Dispatchers.IO) {
            execute(dslContext)
        }.asFlow()
            .catch { it.printStackTrace() }

}
