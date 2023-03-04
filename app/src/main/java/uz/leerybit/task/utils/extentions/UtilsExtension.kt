package uz.leerybit.task.utils.extentions

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.File

operator fun <T : ViewModel> T.invoke(body: T.() -> Unit) = body()

operator fun <V : ViewBinding> V.invoke(init: V.() -> Unit) = init()

fun Context.vibrate(duration: Long) {
    val vibrateService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
        vibrateService.vibrate(
            VibrationEffect.createOneShot(
                duration,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        )
    } else {
        vibrateService.vibrate(duration)
    }
}

fun Context.isPermissionGranted(permission: String): Boolean {
    val selfPermission = ContextCompat.checkSelfPermission(this, permission)
    return selfPermission == PackageManager.PERMISSION_GRANTED
}

fun File.toRequestData(str: String): MultipartBody.Part {
    val requestFile = this.asRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(str, name, requestFile)
}

fun Throwable.errorMessage(): String? {
    return if (this is HttpException) {
        val errorBody = this.response()?.errorBody()?.string()
        try {
            JSONObject(errorBody).getString("message")
        } catch (e: Exception) {
            this.message()
        }
    } else this.message
}

fun View.showPopupMenu(@MenuRes resId: Int, listener: PopupMenu.OnMenuItemClickListener) {
    PopupMenu(context, this).apply {
        inflate(resId)
        setOnMenuItemClickListener(listener)
        show()
    }
}
