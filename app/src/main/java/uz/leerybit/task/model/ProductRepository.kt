package uz.leerybit.task.model

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.leerybit.task.model.remote.ProductApi
import uz.leerybit.task.model.responce.ProductResponce
import javax.inject.Inject


interface ProductRepository {
  suspend fun products(): ProductResponce
}

class ProductRepositoryImpl @Inject constructor(
  private val productApi: ProductApi, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProductRepository {
  override suspend fun products(): ProductResponce = withContext(ioDispatcher) {
    productApi.getProducts()
  }
}