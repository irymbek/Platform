package kz.rymbek.platform.common.core.date

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

object DateUtils {
    private val timeZone = TimeZone.currentSystemDefault()

    val currentInstant: Instant
        get() = Clock.System.now()

    val milliseconds: Long
        get() = currentInstant.toEpochMilliseconds()

    val localDateTime: LocalDateTime
        get() = Clock.System.now().toLocalDateTime(timeZone)

    val localDate: LocalDate
        get() = localDateTime.date

    val year: Int
        get() = localDate.year

    val dayStartMilliseconds: Long
        get() = localDate.atStartOfDayIn(timeZone).toEpochMilliseconds()

    val defaultInstant = Instant.DISTANT_PAST

    fun Long?.toInstant(): Instant {
        return Instant.fromEpochMilliseconds(this ?: 0L)
    }

    fun Long?.toLocalDate(): LocalDate {
        return this.toInstant().toLocalDateTime(timeZone).date
    }

    fun Instant?.toFormattedString(): String {
        return this?.toLocalDateTime(timeZone)?.format(LocalDateTime.Format {
            year()
            char('-')
            monthNumber()
            char('-')
            dayOfMonth()

            char('/')

            hour()
            char(':')
            minute()
            char(':')
            second()
        }).orEmpty()
    }

    fun Long?.toFormattedString(): String {
        return this.toInstant().toFormattedString()
    }
}