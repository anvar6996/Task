package uz.leerybit.task.utils.extentions

import android.content.Context
import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.*

fun String.isEmail(): Boolean {
    return isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isPhone(): Boolean {
    return isNotBlank() && Patterns.PHONE.matcher(this).matches()
}

fun String.toast(context: Context) {
  toast(context, this)
}

fun String.toastLong(context: Context) {
  toastLong(context, this)
}

fun String.date(): Date {
    val formatter = SimpleDateFormat("dd.MM.yyyy hh:mm:ss")
    return formatter.parse(this)
}

fun Date.onlyDateFormat(): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy")
    return formatter.format(this)
}