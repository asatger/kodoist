package org.asatger.kodoist.task

import kotlinx.datetime.*
import org.asatger.kodoist.task.domain.model.TaskDate
import org.asatger.kodoist.task.domain.model.TaskDescription
import org.asatger.kodoist.task.domain.model.TaskTitle
import kotlin.test.*
import kotlin.time.Duration

class CommonTaskTest {

    private fun timeZone() = TimeZone.UTC

    @Test
    fun checkEmptyTitle() {
        val title = TaskTitle()

        assertTrue(title().isEmpty(), "Check title is empty")
    }

    @Test
    fun checkCompleteTitle() {
        var title = TaskTitle("A task title")
        assertEquals(title(), "A task title", "Check title is complete")
        assertNotEquals(title(), "a tAsK TiTle", "Check title case sensitive")
        assertNotEquals(title(), "   A task title   ", "Check title trim")

        title = TaskTitle("   A task title   ")
        assertNotEquals(title(), "   A task title   ", "Check title trim")
    }

    @Test
    fun checkEmptyDescription() {
        val description = TaskDescription()

        assertTrue(description().isEmpty(), "Check description is empty")
    }

    @Test
    fun checkCompleteDescription() {
        var description = TaskDescription("A short description of the task.\n\nWith line breaks.")

        assertEquals(
            description(),
            "A short description of the task.\n\nWith line breaks.",
            "Check description is complete"
        )
        assertNotEquals(
            description(),
            "A short description of the task.\nWith line breaks.",
            "Check description with one line break"
        )
        assertNotEquals(
            description(),
            "a short description of the task.\n\nwith line breaks.",
            "Check description case sensitive"
        )
        assertNotEquals(
            description(),
            "   A short description of the task.\n\nWith line breaks.  ",
            "Check description case trim"
        )

        description = TaskDescription("   A short description of the task.\n\nWith line breaks.  ")

        assertNotEquals(
            description(),
            "   A short description of the task.\n\nWith line breaks.  ",
            "Check description case trim"
        )
    }

    @Test
    fun checkNowCreationDate() {
        val createdAt = TaskDate()
        val nowLocalDateTime = Clock.System.now().toLocalDateTime(timeZone())

        assertEquals(
            createdAt.localDateTime.date,
            nowLocalDateTime.date,
            "Check now date"
        )

        assertEquals(
            createdAt.localDateTime.time.toSecondOfDay(),
            nowLocalDateTime.time.toSecondOfDay(),
            "Check now second of day"
        )
    }

    @Test
    fun checkFutureCreationDate() {
        assertFails("Check creation date in the future") {
            TaskDate.parse(Clock.System.now().plus(Duration.parse("1h")))
        }
    }

    @Test
    fun checkPastCreationDate() {
        val pastDate = LocalDateTime(
            LocalDate(2020, 6, 20),
            LocalTime(22, 41, 23)
        )
        val creationDate = TaskDate.parse(pastDate.toInstant(timeZone()))

        assertTrue(creationDate.isBefore(TaskDate()), "Check creation date is before now")
    }
}