package ru.sboychenko.bpmn.delegate

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.Expression
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.sboychenko.bpmn.NotificationType
import ru.sboychenko.bpmn.Variables
import ru.sboychenko.bpmn.services.EmailServiceImpl

@Component("Notification")
class Notification @Autowired constructor(
        private val notificationService: EmailServiceImpl
) : JavaDelegate {

    val log: Logger = LoggerFactory.getLogger(Notification::class.java)

    private lateinit var template: Expression

    override fun execute(execution: DelegateExecution?) {
        log.info("Execute ${execution!!}")

        val userEmail = execution.getVariable(Variables.USER_EMAIL).toString()
        val userId = execution.getVariable(Variables.USER_ID).toString()
        val sum = execution.getVariable(Variables.SUM).toString()

        notificationService.notify(userEmail, NotificationType.valueOf(template.getValue(execution) as String), mapOf("userId" to userId, "sum" to sum))

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(NotificationType.valueOf("retry"))
        }
    }
}