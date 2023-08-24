package com.example.weathby

import android.content.res.Resources
import android.view.View
import java.text.SimpleDateFormat
import java.util.Locale

fun Float.toPx() = this * Resources.getSystem().displayMetrics.density
fun Float.toDp() = this / Resources.getSystem().displayMetrics.density
fun Int.getDayOfWeek(): String = SimpleDateFormat("EEEE", Locale.ENGLISH).format(this * 1000)
fun Int.getMonthFromTimeStamp(): String = SimpleDateFormat("MMM", Locale.ENGLISH).format(this * 1000)
fun Int.getDayFromTimeStamp(): String = SimpleDateFormat("DD", Locale.ENGLISH).format(this * 1000)
fun Int.getHourFromTimeStamp(): String = SimpleDateFormat("HH:00", Locale.ENGLISH).format(this * 1000)
fun View.setProgressVisibility(condition: () -> Boolean) {
    visibility =  if (condition()) View.VISIBLE else View.GONE
}