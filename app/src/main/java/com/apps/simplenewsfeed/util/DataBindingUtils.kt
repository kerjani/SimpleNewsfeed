package com.apps.simplenewsfeed.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.apps.simplenewsfeed.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.*

@BindingAdapter("imageUrl")
fun setImageUrl(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_baseline_downloading)
                    .error(R.drawable.ic_baseline_warning_amber)
            )
            .into(imgView)
    }
}

fun isoStringToDate(isoDateString: String): Date {
    val temporalAccessor: TemporalAccessor = DateTimeFormatter.ISO_INSTANT.parse(isoDateString)
    val instant: Instant = Instant.from(temporalAccessor)
    return Date.from(instant)
}

private const val SECOND_MILLIS = 1000
private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS

@BindingAdapter("timeAgo")
fun getTimeAgo(textView: TextView, isoDateString: String?) {
    isoDateString?.let {
        val time = isoStringToDate(isoDateString).time
        val now = System.currentTimeMillis()
        if (time > now || time <= 0) {
            textView.text = "invalid future date"
        } else {
            val diff = now - time
            val timeAgoString = when {
                diff < MINUTE_MILLIS -> {
                    textView.context.getString(R.string.ago_now)
                }
                diff < 2 * MINUTE_MILLIS -> {
                    textView.context.getString(R.string.ago_1minute)
                }
                diff < 50 * MINUTE_MILLIS -> {
                    textView.context.getString(R.string.ago_minutes, diff / MINUTE_MILLIS)
                }
                diff < 90 * MINUTE_MILLIS -> {
                    textView.context.getString(R.string.ago_1hour)
                }
                diff < 24 * HOUR_MILLIS -> {
                    textView.context.getString(R.string.ago_hours, diff / HOUR_MILLIS)
                }
                diff < 48 * HOUR_MILLIS -> {
                    textView.context.getString(R.string.ago_1day)
                }
                else -> {
                    textView.context.getString(R.string.ago_days, diff / DAY_MILLIS)
                }
            }
            textView.text = timeAgoString
        }
    }
}