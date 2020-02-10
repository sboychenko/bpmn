package ru.sboychenko.bpmn.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountServiceImpl : AccountService {
    val log: Logger = LoggerFactory.getLogger(AccountServiceImpl::class.java)

    override fun check(userId: String): String {
        log.info("account for $userId exists")
        return "NUM-${createNum()}"
    }

    override fun create(userId: String): String {
        log.info("account for $userId created")
        return "NUM-${createNum()}"
    }

    override fun refund(account: String, sum: Double): Boolean {
        log.info("account $account refund on $sum! Bang!!!")
        return true
    }

    fun createNum(): Int {
        val random = Random()
        return  random.nextInt(100-1) + 1
    }
}