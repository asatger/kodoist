package org.asatger.kodoist.task.domain.model

data class Task(
    val title: TaskTitle = TaskTitle(),
    val description: TaskDescription = TaskDescription(),
    val createdAt: TaskDate = TaskDate()
)
