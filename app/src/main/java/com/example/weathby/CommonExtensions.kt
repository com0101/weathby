package com.example.weathby

import android.content.res.Resources

fun Float.toPx() = this * Resources.getSystem().displayMetrics.density
fun Float.toDp() = this / Resources.getSystem().displayMetrics.density
