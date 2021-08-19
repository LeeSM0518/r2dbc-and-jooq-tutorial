package io.wisoft.exercise.configuration

import io.r2dbc.spi.ConnectionFactory
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JooqConfiguration {
    @Bean
    fun dslContext(connectionFactory: ConnectionFactory): DSLContext =
        DSL.using(connectionFactory)
}
