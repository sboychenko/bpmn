package ru.sboychenko.bpmn.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.sboychenko.bpmn.NotificationType

@Service
class EmailServiceImpl : NotificationService {
    val log: Logger = LoggerFactory.getLogger(EmailServiceImpl::class.java)

    override fun notify(to: String, type: NotificationType, params: Map<String, String>) {

        val text = when (type) {
            NotificationType.processing -> {
                "Добрый день, ${params["userId"]}, запрос еще находится в обработке, ожидайте решения"
            }
            NotificationType.reject -> {
                "Добрый день, ${params["userId"]}, в возмещении на сумму ${params["sum"]} отказано руководителем"
            }
            NotificationType.retry -> {
                "Добрый день, ${params["userId"]}, запрос не обработан, отправьте его заново"
            }
        }

        log.info("Send Email to \"$to\" with text \"$text\"")
    }
}