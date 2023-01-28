package org.asatger.kodoist.task.domain.model

data class TaskDescription(
    private val value: String = ""
) {
    operator fun invoke() = this.value.trim()
}
