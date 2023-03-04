package uz.leerybit.task

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.leerybit.task.databinding.ActivityMainBinding
import uz.leerybit.task.ui.screens.products.ProductListViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
  private val viewModel by viewModels<ProductListViewModel>()
  private val binding by viewBinding(ActivityMainBinding::bind)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    val navHostFragment = supportFragmentManager.findFragmentById(R.id.productsListFragment) as NavHostFragment
//    val navController = navHostFragment.navController
//    navController.setGraph(R.navigation.nav_res)
//    navController.navigate(R.layout.fragment_products_list)
  }
}