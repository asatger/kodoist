package org.asatger.kodoist.task.domain.model

data class TaskTitle(
    private val value: String = ""
) {
    operator fun invoke() = this.value.trim()
}