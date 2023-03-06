package com.coderipper.hsma.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences.Editor

fun saveValue(activity: Activity, values: HashMap<String, Any>) {
    val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
    with (sharedPref.edit()) {
        for ((key, value) in values) {
            when (value) {
                is String -> stringValue(key, value)
                is Int -> intValue(key, value)
                is Long -> longValue(key, value)
                is Boolean -> booleanValue(key, value)
            }
        }
        commit()
    }
}

private fun Editor.stringValue(key: String,  value: String) {
    putString(key, value)
}

private fun Editor.intValue(key: String,  value: Int) {
    putInt(key, value)
}

private fun Editor.longValue(key: String,  value: Long) {
    putLong(key, value)
}

private fun Editor.booleanValue(key: String,  value: Boolean) {
    putBoolean(key, value)
}

fun Activity.stringValue(key: String): String? = getPreferences(Context.MODE_PRIVATE).getString(key, null)

fun Activity.intValue(key: String): Int = getPreferences(Context.MODE_PRIVATE).getInt(key, -1)

fun Activity.longValue(key: String): Long = getPreferences(Context.MODE_PRIVATE).getLong(key, -1)

fun Activity.booleanValue(key: String): Boolean = getPreferences(Context.MODE_PRIVATE).getBoolean(key, false)

fun clearPreferences(activity: Activity) {
    val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
    with (sharedPref.edit()) {
        clear()
        commit()
    }
}