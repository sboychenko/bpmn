package ru.sboychenko.bpmn

import org.camunda.bpm.engine.ExternalTaskService
import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.TaskService
import org.camunda.bpm.engine.exception.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import ru.sboychenko.bpmn.dto.InitDto
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import javax.ws.rs.PathParam
import kotlin.collections.HashMap


@Service
@RestController
class CamundaController @Autowired constructor(
        private val runtimeService: RuntimeService,
        private val taskService: TaskService
) {

    val log: Logger = LoggerFactory.getLogger(CamundaController::class.java)

    @PostMapping("/start")
    fun startProcess(@RequestBody initDto: InitDto): String {
        log.info("Start $initDto")
        val vars = HashMap<String, Any>()

        val uuid = "R-${UUID.randomUUID()}"
        vars[Variables.USER_ID] = initDto.userId
        vars[Variables.USER_EMAIL] = initDto.userEmail
        vars[Variables.SUM] = initDto.sum
        vars[Variables.FOLLOW_UP_DATE] = Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant())

        runtimeService.startProcessInstanceByKey("Process", uuid, vars)
        return "Create $uuid"
    }

    @GetMapping("/chief")
    fun chiefDecisionList(): List<Any> {
        val list = taskService.createTaskQuery().taskCandidateGroup("chief").list()
        return list.map { mapOf("id" to it.id, "createDate" to it.createTime) }.toList()
    }

    @PutMapping("/chief/{id}/approve")
    fun chiefDecisionApprove(@PathVariable id: String): String {
        testTask(id)
        taskService.complete(id, mapOf(Variables.APPROVE to true))
        return "approve ok"
    }

    @PutMapping("/chief/{id}/reject")
    fun chiefDecisionReject(@PathVariable id: String): String {
        testTask(id)
        taskService.complete(id, mapOf(Variables.APPROVE to false))
        return "reject ok"
    }

    fun testTask(id: String): Boolean =
           taskService.createTaskQuery().taskId(id).singleResult().id != null

}