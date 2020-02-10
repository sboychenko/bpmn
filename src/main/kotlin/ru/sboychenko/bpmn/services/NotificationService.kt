package ru.sboychenko.bpmn.services

import ru.sboychenko.bpmn.NotificationType

interface NotificationService {

    fun notify(to: String, type: NotificationType, params: Map<String, String>)
}