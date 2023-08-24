package com.example.weathby

import android.content.res.Resources
import android.view.View
import java.text.SimpleDateFormat
import java.util.Locale

fun Float.toPx() = this * Resources.getSystem().displayMetrics.density
fun Float.toDp() = this / Resources.getSystem().displayMetrics.density
fun Int.getDayOfWeek() = SimpleDateFormat("EEEE", Locale.ENGLISH).format(this * 1000)
fun Int.getMonthFromTimeStamp() = SimpleDateFormat("MMM", Locale.ENGLISH).format(this * 1000)
fun Int.getDayFromTimeStamp() = SimpleDateFormat("DD", Locale.ENGLISH).format(this * 1000)
fun Int.getHourFromTimeStamp() = SimpleDateFormat("HH:00", Locale.ENGLISH).format(this * 1000)
fun View.setProgressVisibility(condition: () -> Boolean) {
    visibility =  if (condition()) View.VISIBLE else View.GONE
}