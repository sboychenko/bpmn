package ru.sboychenko.bpmn.delegate

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.sboychenko.bpmn.Variables
import ru.sboychenko.bpmn.services.AccountService

@Component("CheckAccount")
class CheckAccount @Autowired constructor(
        private val accountService: AccountService
) : JavaDelegate {

    val log: Logger = LoggerFactory.getLogger(CheckAccount::class.java)

    override fun execute(execution: DelegateExecution?) {
        log.info("Execute ${execution!!}")
        val userId = execution.getVariable(Variables.USER_ID).toString()
        log.info("Checking exist account for: $userId")

        val result = accountService.check(userId)

        log.info("Set result for $userId = $result")
        execution.setVariable(Variables.ACCOUNT, result)
    }
}