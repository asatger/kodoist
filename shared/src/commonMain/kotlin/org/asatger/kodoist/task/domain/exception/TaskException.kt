package org.asatger.kodoist.task.domain.exception

sealed class TaskException(message: String) : Exception(message) {

    class CreationDateAfterNowException() : TaskException("Creation date cannot be")
}
