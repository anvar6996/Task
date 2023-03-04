package uz.leerybit.task.utils.extentions

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Size
import android.util.TypedValue
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.view.*
import androidx.fragment.app.FragmentActivity
import kotlin.math.roundToInt
import kotlin.math.roundToLong


val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).roundToInt()

val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

@Suppress("DEPRECATION")
val Context.screenResolution: Size
    get() {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) display
        else wm.defaultDisplay

        val metrics = DisplayMetrics()
        display?.getRealMetrics(metrics)

        val width = metrics.widthPixels
        val height = metrics.heightPixels

        return Size(width, height)
    }

fun View.gone(): View {
    visibility = View.GONE
    return this
}

fun View.invisible(): View {
    visibility = View.INVISIBLE
    return this
}

fun View.visible(): View {
    visibility = View.VISIBLE
    return this
}

fun View.visibleInvisible(isVisible: Boolean): View {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    return this
}

fun View.enableDisable(enable: Boolean): View {
    return if (enable) this.enable() else this.disable()
}

fun View.enable(): View {
    isEnabled = true
    alpha = 1f
    isClickable = true
    return this
}

fun View.disable(): View {
    isEnabled = false
    alpha = 0.5f
    isClickable = false
    return this
}

fun View.updateMargin(
    top: Int = marginTop,
    bottom: Int = marginBottom,
    left: Int = marginLeft,
    right: Int = marginRight
) {
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        topMargin = top
        bottomMargin = bottom
        leftMargin = left
        rightMargin = right
    }
}

fun View.blockClickable(time: Long = 2000) {
    this.isClickable = false
    Handler().postDelayed({ this.isClickable = true }, time)
}

fun View.setSingleClickListener(click: (View) -> Unit) =
    setOnClickListener(SingleClickListener(click))


fun View.hideKeyboard() {
    val imm =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun EditText.showKeyBoard() {
    requestFocus()
    val imm =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

internal fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

val View.inflater get() = LayoutInflater.from(context)

internal fun TextView.setTextColorRes(@ColorRes color: Int) =
    setTextColor(ContextCompat.getColor(context, color))


internal fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)

fun dpToPx(activity: FragmentActivity, dp: Int): Int {
    val displayMetrics = activity.resources.displayMetrics
    return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToLong().toInt()
}


class SingleClickListener(private val click: (v: View) -> Unit) : View.OnClickListener {

    companion object {
        private const val DOUBLE_CLICK_TIMEOUT = 500L
    }

    private var lastClick: Long = 0

    override fun onClick(v: View) {
        if (getLastClickTimeout() > DOUBLE_CLICK_TIMEOUT) {
            lastClick = System.currentTimeMillis()
            click(v)
        }
    }

    private fun getLastClickTimeout(): Long {
        return System.currentTimeMillis() - lastClick
    }
}




