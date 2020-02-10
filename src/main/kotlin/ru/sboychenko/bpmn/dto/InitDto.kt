package ru.sboychenko.bpmn.dto

data class InitDto (
        var userId : String,
        val userEmail : String,
        val sum : Double,
        val purpose: String?
)