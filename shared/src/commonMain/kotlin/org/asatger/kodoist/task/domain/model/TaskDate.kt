package org.asatger.kodoist.task.domain.model

import kotlinx.datetime.*
import org.asatger.kodoist.task.domain.exception.TaskException

class TaskDate {
    private val timeZone = TimeZone.UTC
    val localDateTime: LocalDateTime

    constructor() {
        this.localDateTime = Clock.System.now().toLocalDateTime(timeZone)
    }

    private constructor(instant: Instant) {
        this.localDateTime = instant.toLocalDateTime(timeZone)
        if (this.localDateTime > Clock.System.now().toLocalDateTime(timeZone))
            throw TaskException.CreationDateAfterNowException()
    }

    companion object {
        fun parse(instant: Instant): TaskDate = TaskDate(instant)
    }

    fun isBefore(taskDate: TaskDate): Boolean = this.localDateTime < taskDate.localDateTime

    fun isAfter(taskDate: TaskDate): Boolean = this.localDateTime > taskDate.localDateTime

}


