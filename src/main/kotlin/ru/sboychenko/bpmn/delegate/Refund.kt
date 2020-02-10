package ru.sboychenko.bpmn.delegate

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.sboychenko.bpmn.Variables
import ru.sboychenko.bpmn.services.AccountService

@Component("Refund")
class Refund  @Autowired constructor(
        private val accountService: AccountService
) : JavaDelegate {

    val log: Logger = LoggerFactory.getLogger(Refund::class.java)

    override fun execute(execution: DelegateExecution?) {
        log.info("Execute ${execution!!}")
        val account = execution.getVariable(Variables.ACCOUNT).toString()
        val sum = execution.getVariable(Variables.SUM) as Double

        accountService.refund(account, sum)
    }
}