package ru.sboychenko.bpmn.services

import java.math.BigDecimal

interface AccountService {

    /**
     * Check account existing
     * @param userId
     * @return accountNumber or null if not exist
     */
    fun check(userId: String): String?

    /**
     * Creating account for user
     * @param userId
     * @return account number
     */
    fun create(userId: String): String

    /**
     * Refund sum to account
     * @param account
     * @param sum
     */
    fun refund(account: String, sum: Double): Boolean
}