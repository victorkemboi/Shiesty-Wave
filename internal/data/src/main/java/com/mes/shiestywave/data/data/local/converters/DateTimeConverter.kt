package app.rejareja.data.data.local.converters

import androidx.room.TypeConverter
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.ISODateTimeFormat

/**
 * Static object for converting date to variant types
 */
object DateTimeConverter {

    @TypeConverter
    @JvmStatic
    fun toDate(timestamp: String?): DateTime? = if (
        timestamp.isNullOrEmpty()
    ) {
        null
    } else {
        try {
            DateTimeFormat.forPattern(
                "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ"
            ).parseDateTime(timestamp)
        } catch (e: IllegalArgumentException) {
            DateTimeFormat.forPattern(
                "yyyy-MM-dd'T'HH:mm:ssZ"
            ).parseDateTime(timestamp)
        }
    }

    @TypeConverter
    @JvmStatic
    fun toTimestamp(date: DateTime?): String? = if (date == null) {
        null
    } else {
        ISODateTimeFormat.dateTime().print(date)
    }
}