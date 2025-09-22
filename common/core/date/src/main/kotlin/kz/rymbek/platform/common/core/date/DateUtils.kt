package kz.rymbek.platform.common.core.date

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Instant

object DateUtils {
    private val currentTimeZone: TimeZone
        get() = TimeZone.currentSystemDefault()

    val instant: Instant
        get() = Clock.System.now()

    val milliseconds: Long
        get() = instant.toEpochMilliseconds()

    val localDateTime: LocalDateTime
        get() = instant.toLocalDateTime(currentTimeZone)

    val localDate: LocalDate
        get() = localDateTime.date

    val year: Int
        get() = localDate.year

    val dayStartMilliseconds: Long
        get() = localDate.atStartOfDayIn(currentTimeZone).toEpochMilliseconds()

    val defaultInstant = Instant.DISTANT_PAST
    val defaultLocalDate = defaultInstant.toLocalDateTime(currentTimeZone).date

    fun Long?.toInstantOrDefault(def: Instant = defaultInstant): Instant =
        if (this == null) def else Instant.fromEpochMilliseconds(this)

    fun Long?.toLocalDateOrDefault(): LocalDate {
        return this.toInstantOrDefault().toLocalDateTime(currentTimeZone).date
    }

    fun Instant?.toFormattedString(): String {
        return this?.toLocalDateTime(currentTimeZone)?.format(LocalDateTime.Format {
            year()
            char('-')
            monthNumber()
            char('-')
            day(padding = Padding.ZERO)

            char('/')

            hour()
            char(':')
            minute()
            char(':')
            second()
        }).orEmpty()
    }

    fun Long?.toFormattedString(): String {
        return this.toInstantOrDefault().toFormattedString()
    }
}