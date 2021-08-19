package io.wisoft.exercise.account

import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Component
class AccountRouter(
    private val accountHandler: AccountHandler,
) {

    @Bean
    fun routes(): RouterFunction<ServerResponse> =
        coRouter {
            "/api".nest {
                "/accounts".nest {
                    accept(MediaType.APPLICATION_JSON).nest {
                        POST("", accountHandler::register)
                        GET("", accountHandler::findAll)
                        GET("/{id}", accountHandler::findById)
                        PATCH("/{id}", accountHandler::updateById)
                        DELETE("/{id}", accountHandler::deleteById)
                    }
                }
            }
        }

}
