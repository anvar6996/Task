package uz.leerybit.task.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import dagger.hilt.android.AndroidEntryPoint
import uz.leerybit.task.MainActivity
import uz.leerybit.task.utils.extentions.hideKeyboard
import uz.leerybit.task.utils.extentions.navController
import uz.leerybit.task.utils.extentions.showSnackbar
import uz.leerybit.task.utils.extentions.toast

@AndroidEntryPoint
abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    view.setOnClickListener {
      hideKeyboard()
    }
    initialize()
    initObservers()
    initClicks()
  }

  override fun onDestroy() {
    super.onDestroy()
    hideKeyboard()
  }

  fun NavDirections.navigate() {
    navController.navigate(this)
  }

  protected open fun initialize() {}
  protected open fun initClicks() {}
  protected open fun initObservers() {}

  protected open fun showMessage(message: String) {
    toast(message)
  }

  protected open fun showMessage(@StringRes messageId: Int) {
    val str = getString(messageId)
    showSnackbar(str)
  }

//  protected val mainActivity by lazy { requireActivity() as MainActivity }
}