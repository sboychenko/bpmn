package ru.sboychenko.bpmn

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BpmnApplication

fun main(args: Array<String>) {
    runApplication<BpmnApplication>(*args)
}
