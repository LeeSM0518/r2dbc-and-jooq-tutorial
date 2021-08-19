package io.wisoft.exercise

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApplicationInitializer

fun main(args: Array<String>) {
    runApplication<ApplicationInitializer>(*args)
}
