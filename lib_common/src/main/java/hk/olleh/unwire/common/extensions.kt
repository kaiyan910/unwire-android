package hk.olleh.unwire.common

import androidx.fragment.app.Fragment
import hk.olleh.unwire.common.miscellaneous.TimeAgo
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import timber.log.Timber

fun String.toDateTime(pattern: String): DateTime? = try {
    val formatter = DateTimeFormat.forPattern(pattern)
    formatter.parseDateTime(this)
} catch (e: Exception) {
    Timber.e(e)
    null
}

fun String.findFirstMatchPattern(pattern: String): String? =
    pattern
        .toRegex()
        .find(this)
        ?.groupValues
        ?.get(1)

fun <T> Fragment.argument(key: String) =
    lazy { arguments?.get(key) as T ?: throw IllegalArgumentException("$key is required.") }

fun <T> Fragment.argument(key: String, defaultValue: T) = lazy {
    arguments?.get(key) as? T ?: defaultValue
}

fun Long?.timeAgo() = this?.let { TimeAgo.getTimeAgo(it) } ?: "-"