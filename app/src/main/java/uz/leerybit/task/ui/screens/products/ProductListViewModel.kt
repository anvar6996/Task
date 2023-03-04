package uz.leerybit.task.ui.screens.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.leerybit.task.model.ProductRepository
import uz.leerybit.task.model.responce.ProductResponce
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val productRepository: ProductRepository) :
  ViewModel() {
  private val _uiState = MutableStateFlow(ProductsUIState())
  val uiState = _uiState.asStateFlow()

  fun loadProducts() {
    viewModelScope.launch {
      _uiState.value = ProductsUIState(false, null, productRepository.products())
    }
  }

}

data class ProductsUIState(
  val isLoading: Boolean = false, val message: String? = null, val products: ProductResponce? = null
)