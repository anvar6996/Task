package uz.leerybit.task.utils.extentions

import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

val Fragment.navController get() = findNavController()

fun Fragment.finishFragment() {
    findNavController().popBackStack()
}

fun Fragment.finishFragment(@IdRes destinationId: Int, inclusive: Boolean = false) {
    findNavController().popBackStack(destinationId, inclusive = inclusive)
}

fun Fragment.hideKeyboard() {
    view?.hideKeyboard()
}

fun Fragment.windowAdjustPan() =
    requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

fun Fragment.windowAdjustResize() =
    requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

fun Fragment.toast(message: Int) {
    toast(requireContext(), message)
}
fun Fragment.toast(message: String) {
    toast(requireContext(), message)
}

fun Fragment.showSnackbar(message: String, view: View) {
    showSnackbar(message, view)
}

fun Fragment.showSnackbar(message: String) {
    view?.let {
        showSnackbar(message, it)
    }
}

fun Fragment.getColor(@ColorRes c: Int) = ContextCompat.getColor(requireContext(), c)


fun Fragment.inDevelopment() {
    toast(requireContext(), "In Development")
}


