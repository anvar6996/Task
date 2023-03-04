package uz.leerybit.task.ui.screens.products

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import uz.leerybit.task.R
import uz.leerybit.task.databinding.FragmentProductsListBinding
import uz.leerybit.task.ui.base.BaseFragment
import uz.leerybit.task.utils.extentions.launchWhenStarted

@AndroidEntryPoint
class ProductsListFragment : BaseFragment(R.layout.fragment_products_list) {
  private val bind by viewBinding(FragmentProductsListBinding::bind)
  private val adatperProduct = ProductsAdapter()
  private val productsListViewModel by viewModels<ProductListViewModel>()

  override fun initialize() {
    super.initialize()
    bind.apply {
      recyklerProducts.adapter = adatperProduct
      recyklerProducts.layoutManager = LinearLayoutManager(requireContext())
      productsListViewModel.loadProducts()
    }
  }

  override fun initClicks() {
    super.initClicks()
    adatperProduct.setItemClickListener {
      findNavController().navigate(
        ProductsListFragmentDirections.actionProductsListFragmentToProductDetailsFragment(it)
      )
    }
  }

  override fun initObservers() {
    super.initObservers()
    productsListViewModel.uiState.onEach {
      adatperProduct.submitList(it.products?.offers)
    }.launchWhenStarted(lifecycleScope)
  }
}