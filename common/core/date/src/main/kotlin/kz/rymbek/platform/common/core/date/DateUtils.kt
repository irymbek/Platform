package kz.rymbek.platform.common.core.date

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.Instant
import kotlin.time.toDuration

object DateUtils {
    private val RUSSIAN_ABBREVIATED: MonthNames = MonthNames(
        listOf(
            "Янв", "Фев", "Мар", "Апр", "Май", "Июн",
            "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"
        )
    )

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

    val hour: Int
        get() = localDateTime.hour

    val minute: Int
        get() = localDateTime.minute

    val dayStartMilliseconds: Long
        get() = localDate.atStartOfDayIn(currentTimeZone).toEpochMilliseconds()

    val defaultInstant: Instant = Instant.DISTANT_PAST
    val defaultLocalDateTime: LocalDateTime = defaultInstant.toLocalDateTime(currentTimeZone)
    val defaultLocalDate: LocalDate = defaultLocalDateTime.date

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

    fun Instant?.toFormattedMonth(): String = this
            ?.toLocalDateTime(currentTimeZone)
            ?.format(
                LocalDateTime.Format {
                    day(padding = Padding.ZERO)
                    char(' ')
                    monthName(RUSSIAN_ABBREVIATED)
                    char('.')
                    char(' ')
                    year()
                }
            ).orEmpty()

    fun Duration.toFormattedString(): String =
        this.toComponents { minutes, seconds, _ ->
            "%02d:%02d".format(minutes, seconds)
        }

    fun Long?.toFormattedString(): String {
        return this.toInstantOrDefault().toFormattedString()
    }

    fun String.toDuration() = this.toLong().toDuration(DurationUnit.SECONDS)
}